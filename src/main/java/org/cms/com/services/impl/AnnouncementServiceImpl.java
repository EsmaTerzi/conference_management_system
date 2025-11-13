package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Announcement;
import org.cms.com.domain.Conference;
import org.cms.com.models.dto.AnnouncementDto;
import org.cms.com.models.dto.CreateAnnouncementRequest;
import org.cms.com.repositories.AnnouncementRepository;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.services.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public AnnouncementDto create(CreateAnnouncementRequest request) {
        Announcement announcement = new Announcement();
        announcement.setTitle(request.getAnnouncementTitle());
        announcement.setDescription(request.getAnnouncementDesc());
        announcement.setDate(request.getAnnouncementDate() != null ?
                request.getAnnouncementDate() : LocalDateTime.now());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            announcement.setConference(conference);
        }

        Announcement saved = announcementRepository.save(announcement);
        return toDto(saved);
    }

    @Override
    public AnnouncementDto update(Long id, CreateAnnouncementRequest request) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));

        announcement.setTitle(request.getAnnouncementTitle());
        announcement.setDescription(request.getAnnouncementDesc());
        announcement.setDate(request.getAnnouncementDate() != null ?
                request.getAnnouncementDate() : announcement.getDate());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            announcement.setConference(conference);
        }

        Announcement updated = announcementRepository.save(announcement);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public AnnouncementDto get(Long id) {
        return announcementRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
    }

    @Override
    public Page<AnnouncementDto> listAll(Pageable pageable) {
        return announcementRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<AnnouncementDto> getByConferenceId(Long conferenceId, Pageable pageable) {
        return announcementRepository.findByConferenceId(conferenceId, pageable)
                .map(this::toDto);
    }

    private AnnouncementDto toDto(Announcement announcement) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setId(announcement.getId());
        dto.setAnnouncementTitle(announcement.getTitle());
        dto.setAnnouncementDesc(announcement.getDescription());
        dto.setAnnouncementDate(announcement.getDate());

        if (announcement.getConference() != null) {
            dto.setConferenceId(announcement.getConference().getId());
        }

        return dto;
    }
}

