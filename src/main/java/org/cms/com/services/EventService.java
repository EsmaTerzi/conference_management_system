package org.cms.com.services;

import org.cms.com.models.dto.EventDto;
import org.cms.com.models.dto.CreateEventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    EventDto create(CreateEventRequest request);
    EventDto update(Long id, CreateEventRequest request);
    void delete(Long id);
    EventDto get(Long id);
    Page<EventDto> listAll(Pageable pageable);
    Page<EventDto> getByProgramId(Long programId, Pageable pageable);
}
