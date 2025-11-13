package org.cms.com.services;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.models.dto.ConferenceDto;
import org.cms.com.models.dto.CreateConferenceRequest;
import org.cms.com.repositories.ConferenceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Override
    public ConferenceDto create(CreateConferenceRequest request) {
        Conference entity = ConferenceMapper.toEntity(request);
        return ConferenceMapper.toDto(conferenceRepository.save(entity));
    }

    @Override
    public ConferenceDto update(Long id, SaveConferenceRequest request) {
        Conference entity = conferenceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Conference not found"));
        ConferenceMapper.updateEntity(entity, request);
        return ConferenceMapper.toDto(conferenceRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        conferenceRepository.deleteById(id);
    }

    @Override
    public ConferenceDto get(Long id) {
        return conferenceRepository.findById(id)
                .map(ConferenceMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Conference not found"));
    }

    @Override
    public List<ConferenceDto> listAll() {
        return conferenceRepository.findAll().stream()
                .map(ConferenceMapper::toDto)
                .toList();
    }
}
