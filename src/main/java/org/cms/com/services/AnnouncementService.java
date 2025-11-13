package org.cms.com.services;

import org.cms.com.models.dto.AnnouncementDto;
import org.cms.com.models.dto.CreateAnnouncementRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementService {
    AnnouncementDto create(CreateAnnouncementRequest request);
    AnnouncementDto update(Long id, CreateAnnouncementRequest request);
    void delete(Long id);
    AnnouncementDto get(Long id);
    Page<AnnouncementDto> listAll(Pageable pageable);
    Page<AnnouncementDto> getByConferenceId(Long conferenceId, Pageable pageable);
}

