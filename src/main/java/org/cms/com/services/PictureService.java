package org.cms.com.services;

import org.cms.com.models.dto.PictureDto;
import org.cms.com.models.dto.CreatePictureRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PictureService {
    PictureDto create(CreatePictureRequest request);
    PictureDto update(Long id, CreatePictureRequest request);
    void delete(Long id);
    PictureDto get(Long id);
    Page<PictureDto> listAll(Pageable pageable);
    Page<PictureDto> getByConferenceId(Long conferenceId, Pageable pageable);
}

