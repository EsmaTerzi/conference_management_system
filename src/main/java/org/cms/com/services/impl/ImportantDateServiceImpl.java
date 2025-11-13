package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.ImportantDate;
import org.cms.com.models.dto.CreateImportantDateRequest;
import org.cms.com.models.dto.ImportantDateDto;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.repositories.ImportantDateRepository;
import org.cms.com.services.ImportantDateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ImportantDateServiceImpl implements ImportantDateService {

    private final ImportantDateRepository importantDateRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public ImportantDateDto create(CreateImportantDateRequest request) {
        ImportantDate importantDate = new ImportantDate();
        importantDate.setTitle(request.getImpTitle());
        importantDate.setDate(request.getImpDate());
        importantDate.setCreatedAt(LocalDateTime.now());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            importantDate.setConference(conference);
        }

        ImportantDate saved = importantDateRepository.save(importantDate);
        return toDto(saved);
    }

    @Override
    public ImportantDateDto update(Long id, CreateImportantDateRequest request) {
        ImportantDate importantDate = importantDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ImportantDate not found"));

        importantDate.setTitle(request.getImpTitle());
        importantDate.setDate(request.getImpDate());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            importantDate.setConference(conference);
        }

        ImportantDate updated = importantDateRepository.save(importantDate);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        importantDateRepository.deleteById(id);
    }

    @Override
    public ImportantDateDto get(Long id) {
        return importantDateRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("ImportantDate not found"));
    }

    @Override
    public Page<ImportantDateDto> listAll(Pageable pageable) {
        return importantDateRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<ImportantDateDto> getByConferenceId(Long conferenceId, Pageable pageable) {
        return importantDateRepository.findByConferenceId(conferenceId, pageable)
                .map(this::toDto);
    }

    private ImportantDateDto toDto(ImportantDate importantDate) {
        ImportantDateDto dto = new ImportantDateDto();
        dto.setId(importantDate.getId());
        dto.setImpTitle(importantDate.getTitle());
        dto.setImpDate(importantDate.getDate());

        if (importantDate.getConference() != null) {
            dto.setConferenceId(importantDate.getConference().getId());
        }

        return dto;
    }
}

