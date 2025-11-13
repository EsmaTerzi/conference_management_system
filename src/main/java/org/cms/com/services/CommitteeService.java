package org.cms.com.services;

import org.cms.com.models.dto.CommitteeDto;
import org.cms.com.models.dto.CreateCommitteeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommitteeService {
    CommitteeDto create(CreateCommitteeRequest request);
    CommitteeDto update(Long id, CreateCommitteeRequest request);
    void delete(Long id);
    CommitteeDto get(Long id);
    Page<CommitteeDto> listAll(Pageable pageable);
    Page<CommitteeDto> getByConferenceId(Long conferenceId, Pageable pageable);
    Page<CommitteeDto> getByConferenceIdAndType(Long conferenceId, String committeeType, Pageable pageable);
}

