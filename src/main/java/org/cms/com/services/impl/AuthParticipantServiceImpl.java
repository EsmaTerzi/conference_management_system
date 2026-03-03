package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.*;
import org.cms.com.models.dto.*;
import org.cms.com.repositories.CommitteeRepository;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.repositories.ParticipantRepository;
import org.cms.com.security.JwtService;
import org.cms.com.security.ParticipantDetailsService;
import org.cms.com.services.IAuthParticipantService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthParticipantServiceImpl implements IAuthParticipantService {

    private final ParticipantRepository participantRepository;
    private final ConferenceRepository conferenceRepository;
    private final CommitteeRepository committeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ParticipantDetailsService participantDetailsService;

    @Override
    public AuthResponse registerParticipant(ParticipantRegisterRequest request) {
        // Email kontrolü
        if (participantRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Conference'ı bul
        Conference conference = conferenceRepository.findById(request.getConferenceId())
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        // Bilim Kurulu veya Düzenleme Kurulu üyesi olarak kayıt olmak istiyorsa Committee kontrolü yap
        String participationType = request.getTypeOfParticipation();

        // TypeOfParticipation değerini normalize et (hem BILIM hem "Bilim Kurulu Üyesi" kabul edilsin)
        boolean isScienceCommittee = "Bilim Kurulu Üyesi".equalsIgnoreCase(participationType) ||
                                     "BILIM".equalsIgnoreCase(participationType);
        boolean isOrganizingCommittee = "Düzenleme Kurulu Üyesi".equalsIgnoreCase(participationType) ||
                                        "DUZENLEME".equalsIgnoreCase(participationType);

        if (isScienceCommittee || isOrganizingCommittee) {
            // Committee tablosunda bu email ile kayıt var mı kontrol et
            Committee committee = committeeRepository.findByEmailAndConference_Id(request.getEmail(), request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException(
                            "Bu konferans için " + participationType + " olarak kayıt bulunamadı. " +
                            "Lütfen konferans düzenleyicisi ile iletişime geçin."
                    ));

            // Committee type kontrolü (BILIM veya DUZENLEME olmalı)
            String expectedCommitteeType = isScienceCommittee ? "BILIM" : "DUZENLEME";
            if (!expectedCommitteeType.equalsIgnoreCase(committee.getCommitteeType())) {
                throw new RuntimeException(
                        "Kayıt türünüz (" + participationType + ") ile committee kaydınız (" +
                        committee.getCommitteeType() + ") uyuşmuyor."
                );
            }
        }

        // Yeni kullanıcı oluştur
        Participant participant = new Participant();
        participant.setName(request.getName());
        participant.setSurname(request.getSurname());
        participant.setOrganisation(request.getOrganisation());
        participant.setEmail(request.getEmail());
        participant.setTitle(request.getTitle());
        participant.setTypeOfParticipation(request.getTypeOfParticipation());
        participant.setPassword(passwordEncoder.encode(request.getPassword()));

        // Öğrenci veya IEEE Üyesi ise belge kontrolü
        if("Öğrenci".equals(request.getTypeOfParticipation()) || "IEEE Üyesi".equals(request.getTypeOfParticipation())){
            participant.setDocumentUrl(request.getDocumentUrl());
            participant.setDocumentStatus(DocumentStatus.valueOf(request.getDocumentStatus()));
        }

        // Conference'ı ekle
        participant.getConferences().add(conference);

        Participant savedParticipant = participantRepository.save(participant);

        // JWT token oluştur
        UserDetails userDetails = participantDetailsService.loadUserByUsername(savedParticipant.getEmail());
        String token = jwtService.generateToken(userDetails);

        // Response oluştur
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setPersonId(savedParticipant.getId());
        response.setFullName(savedParticipant.getName() + " " + savedParticipant.getSurname());

        return response;
    }

    @Override
    public AuthResponse loginParticipant(ParticipantLoginRequest request){
        // Kullanıcıyı email ile bul
        Participant participant = participantRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Şifre kontrolü
        if (!passwordEncoder.matches(request.getPassword(), participant.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // JWT token oluştur
        UserDetails userDetails = participantDetailsService.loadUserByUsername(participant.getEmail());
        String token = jwtService.generateToken(userDetails);

        // Response oluştur
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setPersonId(participant.getId());
        response.setFullName(participant.getName() + " " + participant.getSurname());

        return response;
    }

    @Override
    public ProfileResponse getProfile(String email) {
        Participant participant = participantRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ProfileResponse.builder()
                .id(participant.getId())
                .name(participant.getName())
                .surname(participant.getSurname())
                .organisation(participant.getOrganisation())
                .email(participant.getEmail())
                .title(participant.getTitle())
                .typeOfParticipation(participant.getTypeOfParticipation())
                .password(participant.getPassword())
                .documentStatus(participant.getDocumentStatus())
                .build();
    }


    @Override
    public void updatePassword(String email, UpdatePasswordRequest request) {
        Participant participant = participantRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mevcut şifreyi kontrol et
        if (!passwordEncoder.matches(request.getCurrentPassword(), participant.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        // Yeni şifreyi encode et ve kaydet
        participant.setPassword(passwordEncoder.encode(request.getNewPassword()));
        participantRepository.save(participant);
    }



}
