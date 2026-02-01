package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.DocumentStatus;
import org.cms.com.domain.Participant;
import org.cms.com.models.dto.AuthResponse;
import org.cms.com.models.dto.ParticipantRegisterRequest;
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
public class AuthParticipateServiceImpl implements IAuthParticipantService {

    private final ParticipantRepository participantRepository;
    private final ConferenceRepository conferenceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ParticipantDetailsService participantDetailsService;

    @Override
    public AuthResponse registerParticipent(ParticipantRegisterRequest request) {
        // Email kontrolü
        if (participantRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Conference'ı bul
        Conference conference = conferenceRepository.findById(request.getConferenceId())
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        // Yeni kullanıcı oluştur
        Participant participant = new Participant();
        participant.setName(request.getName());
        participant.setSurname(request.getSurname());
        participant.setOrganisation(request.getOrganisation());
        participant.setEmail(request.getEmail());
        participant.setTitle(request.getTitle());
        participant.setTypeOfParticipation(request.getTypeOfParticipation());
        participant.setPassword(passwordEncoder.encode(request.getPassword()));
        if(request.getTypeOfParticipation().equals("Öğrenci") || request.getTypeOfParticipation().equals("IEEE Üyesi")){
            participant.setDocumentUrl(request.getDocumentUrl());
            participant.setDocumentStatus(DocumentStatus.valueOf(request.getDocumentStatus()));
        }

        // Conference'ı ekle
        participant.getConferences().add(conference);

        Participant savedParticipant = participantRepository.save(participant);

        // JWT token oluştur - ParticipantDetailsService kullan
        UserDetails userDetails = participantDetailsService.loadUserByUsername(savedParticipant.getEmail());
        String token = jwtService.generateToken(userDetails);

        // Response oluştur
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setPersonId(savedParticipant.getId());
        response.setFullName(savedParticipant.getName() + " " + savedParticipant.getSurname());

        return response;
    }
}
