package org.cms.com.services;

import org.cms.com.models.dto.ConferenceDto;
import org.cms.com.models.dto.CreateConferenceRequest;

import java.util.List;

// ConferenceService.java
public interface ConferenceService {
    ConferenceDto create(CreateConferenceRequest request);
    ConferenceDto update(Long id, CreateConferenceRequest request); // update de aynı DTO'yu kullanır
    void delete(Long id);
    ConferenceDto get(Long id);
    List<ConferenceDto> listAll();
}


