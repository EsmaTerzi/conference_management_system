package org.cms.com.services;

import org.cms.com.models.dto.PersonConferenceDto;
import org.cms.com.models.dto.CreatePersonConferenceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonConferenceService {
    PersonConferenceDto create(CreatePersonConferenceRequest request);
    PersonConferenceDto update(Long id, CreatePersonConferenceRequest request);
    void delete(Long id);
    PersonConferenceDto get(Long id);
    Page<PersonConferenceDto> listAll(Pageable pageable);
    Page<PersonConferenceDto> getByConferenceId(Long conferenceId, Pageable pageable);
    Page<PersonConferenceDto> getByPersonId(Long personId, Pageable pageable);
}

