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
        conference.setName(request.getConferenceName());
        conference.setDescription(request.getDescription());
        conference.setLocation(request.getLocation());
        conference.setStartDate(request.getStartDate());
        conference.setEndDate(request.getEndDate());
        conference.setLogoPath(request.getLogoPath());
        conference.setCoverPath(request.getCoverPath());

        Conference saved = conferenceRepository.save(conference);
        return toDto(saved);
    }

    @Override
    public ConferenceDto update(Long id, CreateConferenceRequest request) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        conference.setName(request.getConferenceName());
        conference.setDescription(request.getDescription());
        conference.setLocation(request.getLocation());
        conference.setStartDate(request.getStartDate());
        conference.setEndDate(request.getEndDate());
        conference.setLogoPath(request.getLogoPath());
        conference.setCoverPath(request.getCoverPath());

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
        return conferenceRepository.findByOwnerId(ownerId, pageable)
                .map(this::toDto);
    }

    private ConferenceDto toDto(Conference conference) {
        ConferenceDto dto = new ConferenceDto();
        dto.setId(conference.getId());
        dto.setConferenceName(conference.getName());
        dto.setDescription(conference.getDescription());
        dto.setLocation(conference.getLocation());
        dto.setStartDate(conference.getStartDate());
        dto.setEndDate(conference.getEndDate());
        dto.setLogoPath(conference.getLogoPath());
        dto.setCoverPath(conference.getCoverPath());
        return dto;
    }
}

