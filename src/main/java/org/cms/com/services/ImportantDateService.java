package org.cms.com.services;

import org.cms.com.models.dto.ImportantDateDto;
import org.cms.com.models.dto.CreateImportantDateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImportantDateService {
    ImportantDateDto create(CreateImportantDateRequest request);
    ImportantDateDto update(Long id, CreateImportantDateRequest request);
    void delete(Long id);
    ImportantDateDto get(Long id);
    Page<ImportantDateDto> listAll(Pageable pageable);
    Page<ImportantDateDto> getByConferenceId(Long conferenceId, Pageable pageable);
}

