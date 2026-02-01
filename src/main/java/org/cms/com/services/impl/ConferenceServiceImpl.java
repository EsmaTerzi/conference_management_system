package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.Person;
import org.cms.com.domain.PersonConference;
import org.cms.com.models.dto.ConferenceDto;
import org.cms.com.models.dto.CreateConferenceRequest;
import org.cms.com.repositories.*;
import org.cms.com.services.IConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements IConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final PersonRepository personRepository;
    private final PersonConferenceRepository personConferenceRepository;
    private final ProgramRepository programRepository;
    private final AnnouncementRepository announcementRepository;
    private final CommitteeRepository committeeRepository;
    private final ImportantDateRepository importantDateRepository;
    private final SponsorRepository sponsorRepository;

    @Override
    public ConferenceDto create(CreateConferenceRequest request) {
        Conference conference = new Conference();

        // Oturum açmış kullanıcıyı owner olarak ata
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Person owner = personRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
        conference.setOwner(owner);

        // temel bilgiler
        conference.setConferenceName(request.getConferenceName());
        conference.setShortSubtitle(request.getShortSubtitle());
        conference.setDescription(request.getDescription());
        conference.setLocation(request.getLocation());
        conference.setStartDate(request.getStartDate());
        conference.setEndDate(request.getEndDate());
        conference.setLogoPath(request.getLogoPath());
        conference.setCoverPath(request.getCoverPath());

        // footer bilgileri
        conference.setFooterOrganizationTitle(request.getFooterOrganizationTitle());
        conference.setFooterAddress(request.getFooterAddress());
        conference.setFooterCityCountry(request.getFooterCityCountry());
        conference.setFooterYearText(request.getFooterYearText());
        conference.setFooterPhone(request.getFooterPhone());
        conference.setFooterEmail(request.getFooterEmail());
        conference.setFooterFacebookUrl(request.getFooterFacebookUrl());
        conference.setFooterTwitterUrl(request.getFooterTwitterUrl());
        conference.setFooterInstagramUrl(request.getFooterInstagramUrl());
        conference.setFooterLinkedinUrl(request.getFooterLinkedinUrl());

        Conference saved = conferenceRepository.save(conference);

        // Konferans sahibini otomatik olarak PersonConference tablosuna ekle
        PersonConference personConference = new PersonConference();
        personConference.setConference(saved);
        personConference.setPerson(owner);
        personConferenceRepository.save(personConference);

        // Sponsorları kaydet
        if (request.getSponsors() != null && !request.getSponsors().isEmpty()) {
            request.getSponsors().forEach(sponsorDto -> {
                org.cms.com.domain.Sponsor sponsor = new org.cms.com.domain.Sponsor();
                sponsor.setName(sponsorDto.getName());
                sponsor.setType(sponsorDto.getType());
                sponsor.setLogoUrl(sponsorDto.getLogoUrl());
                sponsor.setConference(saved);
                sponsorRepository.save(sponsor);
            });
        }

        return toDto(saved);
    }

    @Override
    public ConferenceDto update(Long id, CreateConferenceRequest request) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        // Güvenlik kontrolü: Sadece konferans sahibi güncelleyebilir
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Person currentUser = personRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        if (conference.getOwner() == null || !conference.getOwner().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to update this conference");
        }

        // temel bilgiler - sadece null olmayanları güncelle
        if (request.getConferenceName() != null) {
            conference.setConferenceName(request.getConferenceName());
        }
        if (request.getShortSubtitle() != null) {
            conference.setShortSubtitle(request.getShortSubtitle());
        }
        if (request.getDescription() != null) {
            conference.setDescription(request.getDescription());
        }
        if (request.getLocation() != null) {
            conference.setLocation(request.getLocation());
        }
        if (request.getStartDate() != null) {
            conference.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            conference.setEndDate(request.getEndDate());
        }
        if (request.getLogoPath() != null) {
            conference.setLogoPath(request.getLogoPath());
        }
        if (request.getCoverPath() != null) {
            conference.setCoverPath(request.getCoverPath());
        }

        // footer bilgileri - sadece null olmayanları güncelle
        if (request.getFooterOrganizationTitle() != null) {
            conference.setFooterOrganizationTitle(request.getFooterOrganizationTitle());
        }
        if (request.getFooterAddress() != null) {
            conference.setFooterAddress(request.getFooterAddress());
        }
        if (request.getFooterCityCountry() != null) {
            conference.setFooterCityCountry(request.getFooterCityCountry());
        }
        if (request.getFooterYearText() != null) {
            conference.setFooterYearText(request.getFooterYearText());
        }
        if (request.getFooterPhone() != null) {
            conference.setFooterPhone(request.getFooterPhone());
        }
        if (request.getFooterEmail() != null) {
            conference.setFooterEmail(request.getFooterEmail());
        }
        if (request.getFooterFacebookUrl() != null) {
            conference.setFooterFacebookUrl(request.getFooterFacebookUrl());
        }
        if (request.getFooterTwitterUrl() != null) {
            conference.setFooterTwitterUrl(request.getFooterTwitterUrl());
        }
        if (request.getFooterInstagramUrl() != null) {
            conference.setFooterInstagramUrl(request.getFooterInstagramUrl());
        }
        if (request.getFooterLinkedinUrl() != null) {
            conference.setFooterLinkedinUrl(request.getFooterLinkedinUrl());
        }

        // Sponsorları güncelle (eğer gönderildiyse)
        if (request.getSponsors() != null) {
            // Mevcut sponsorları sil
            List<org.cms.com.domain.Sponsor> existingSponsors = sponsorRepository.findByConference_Id(id);
            sponsorRepository.deleteAll(existingSponsors);

            // Yeni sponsorları ekle
            request.getSponsors().forEach(sponsorDto -> {
                org.cms.com.domain.Sponsor sponsor = new org.cms.com.domain.Sponsor();
                sponsor.setName(sponsorDto.getName());
                sponsor.setType(sponsorDto.getType());
                sponsor.setLogoUrl(sponsorDto.getLogoUrl());
                sponsor.setConference(conference);
                sponsorRepository.save(sponsor);
            });
        }

        // NOT: Owner değiştirilmez - güvenlik için

        Conference updated = conferenceRepository.save(conference);
        return toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Konferans var mı kontrol et
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        // Güvenlik kontrolü: Sadece konferans sahibi silebilir
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Person currentUser = personRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        if (conference.getOwner() == null || !conference.getOwner().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to delete this conference");
        }


        // 2. Program'ları sil
        programRepository.deleteAll(programRepository.findByConference_Id(id, Pageable.unpaged()));

        // 3. PersonConference kayıtlarını sil
        personConferenceRepository.deleteAll(
                personConferenceRepository.findByConference_Id(id, Pageable.unpaged())
        );

        // 4. Announcement'ları sil
        announcementRepository.deleteAll(
                announcementRepository.findByConference_Id(id, Pageable.unpaged())
        );

        // 5. Committee'leri sil
        committeeRepository.deleteAll(
                committeeRepository.findByConference_Id(id, Pageable.unpaged())
        );

        // 7. ImportantDate'leri sil
        importantDateRepository.deleteAll(
                importantDateRepository.findByConference_Id(id, Pageable.unpaged())
        );

        // 8. Sponsor'ları sil
        sponsorRepository.deleteAll(
                sponsorRepository.findByConference_Id(id)
        );

        // 9. Son olarak Conference'ı sil
        conferenceRepository.deleteById(id);
    }

    @Override
    public ConferenceDto get(Long id) {
        return conferenceRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
    }

    @Override
    public Page<ConferenceDto> listAll(Pageable pageable) {
        return conferenceRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<ConferenceDto> getByOwnerId(Long ownerId, Pageable pageable) {
        return conferenceRepository.findByOwner_Id(ownerId, pageable)
                .map(this::toDto);
    }

    private ConferenceDto toDto(Conference conference) {
        ConferenceDto dto = new ConferenceDto();
        dto.setId(conference.getId());
        dto.setConferenceName(conference.getConferenceName());
        dto.setShortSubtitle(conference.getShortSubtitle());
        dto.setDescription(conference.getDescription());
        dto.setLocation(conference.getLocation());
        dto.setStartDate(conference.getStartDate());
        dto.setEndDate(conference.getEndDate());
        dto.setLogoPath(conference.getLogoPath());
        dto.setCoverPath(conference.getCoverPath());

        // owner bilgileri
        if (conference.getOwner() != null) {
            dto.setOwnerId(conference.getOwner().getId());
            dto.setOwnerEmail(conference.getOwner().getEmail());
            dto.setOwnerName(conference.getOwner().getName() + " " + conference.getOwner().getSurname());
        }

        // footer alanları
        dto.setFooterOrganizationTitle(conference.getFooterOrganizationTitle());
        dto.setFooterAddress(conference.getFooterAddress());
        dto.setFooterCityCountry(conference.getFooterCityCountry());
        dto.setFooterYearText(conference.getFooterYearText());
        dto.setFooterPhone(conference.getFooterPhone());
        dto.setFooterEmail(conference.getFooterEmail());
        dto.setFooterFacebookUrl(conference.getFooterFacebookUrl());
        dto.setFooterTwitterUrl(conference.getFooterTwitterUrl());
        dto.setFooterInstagramUrl(conference.getFooterInstagramUrl());
        dto.setFooterLinkedinUrl(conference.getFooterLinkedinUrl());

        return dto;
    }
}
