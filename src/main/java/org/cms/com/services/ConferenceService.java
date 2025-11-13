package org.cms.com.services;

import org.cms.com.models.dto.ConferenceDto;
import org.cms.com.models.dto.CreateConferenceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConferenceService {
    ConferenceDto create(CreateConferenceRequest request);
    ConferenceDto update(Long id, CreateConferenceRequest request);
    void delete(Long id);
    ConferenceDto get(Long id);
    Page<ConferenceDto> listAll(Pageable pageable);
    Page<ConferenceDto> getByOwnerId(Long ownerId, Pageable pageable);
}
