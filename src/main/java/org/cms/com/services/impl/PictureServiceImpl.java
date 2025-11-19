package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.Picture;
import org.cms.com.models.dto.CreatePictureRequest;
import org.cms.com.models.dto.PictureDto;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.repositories.PictureRepository;
import org.cms.com.services.PictureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public PictureDto create(CreatePictureRequest request) {
        Picture picture = new Picture();
        picture.setFilePath(request.getFilePath());
        picture.setCaption(request.getCaption());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            picture.setConference(conference);
        }

        Picture saved = pictureRepository.save(picture);
        return toDto(saved);
    }

    @Override
    public PictureDto update(Long id, CreatePictureRequest request) {
        Picture picture = pictureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Picture not found"));

        picture.setFilePath(request.getFilePath());
        picture.setCaption(request.getCaption());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            picture.setConference(conference);
        }

        Picture updated = pictureRepository.save(picture);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public PictureDto get(Long id) {
        return pictureRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Picture not found"));
    }

    @Override
    public Page<PictureDto> listAll(Pageable pageable) {
        return pictureRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<PictureDto> getByConferenceId(Long conferenceId, Pageable pageable) {
        return pictureRepository.findByConference_Id(conferenceId, pageable)
                .map(this::toDto);
    }

    private PictureDto toDto(Picture picture) {
        PictureDto dto = new PictureDto();
        dto.setId(picture.getId());
        dto.setFilePath(picture.getFilePath());
        dto.setCaption(picture.getCaption());

        if (picture.getConference() != null) {
            dto.setConferenceId(picture.getConference().getId());
        }

        return dto;
    }
}

