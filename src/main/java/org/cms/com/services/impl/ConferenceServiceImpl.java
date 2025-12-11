package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.models.dto.ConferenceDto;
import org.cms.com.models.dto.CreateConferenceRequest;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.services.ConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Override
    public ConferenceDto create(CreateConferenceRequest request) {
        Conference conference = new Conference();

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
        return toDto(saved);
    }

    @Override
    public ConferenceDto update(Long id, CreateConferenceRequest request) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

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

        Conference updated = conferenceRepository.save(conference);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
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
