package org.cms.com.services;

import org.cms.com.models.dto.ProgramDto;
import org.cms.com.models.dto.CreateProgramRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramService {
    ProgramDto create(CreateProgramRequest request);
    ProgramDto update(Long id, CreateProgramRequest request);
    void delete(Long id);
    ProgramDto get(Long id);
    Page<ProgramDto> listAll(Pageable pageable);
    Page<ProgramDto> getByConferenceId(Long conferenceId, Pageable pageable);
}
