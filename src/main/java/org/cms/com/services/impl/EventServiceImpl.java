package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Event;
import org.cms.com.domain.Program;
import org.cms.com.models.dto.CreateEventRequest;
import org.cms.com.models.dto.EventDto;
import org.cms.com.repositories.EventRepository;
import org.cms.com.repositories.ProgramRepository;
import org.cms.com.services.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ProgramRepository programRepository;

    @Override
    public EventDto create(CreateEventRequest request) {
        Event event = new Event();
        event.setTitle(request.getEventTitle());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());

        if (request.getProgramId() != null) {
            Program program = programRepository.findById(request.getProgramId())
                    .orElseThrow(() -> new RuntimeException("Program not found"));
            event.setProgram(program);
        }

        Event saved = eventRepository.save(event);
        return toDto(saved);
    }

    @Override
    public EventDto update(Long id, CreateEventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setTitle(request.getEventTitle());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());

        if (request.getProgramId() != null) {
            Program program = programRepository.findById(request.getProgramId())
                    .orElseThrow(() -> new RuntimeException("Program not found"));
            event.setProgram(program);
        }

        Event updated = eventRepository.save(event);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public EventDto get(Long id) {
        return eventRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public Page<EventDto> listAll(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<EventDto> getByProgramId(Long programId, Pageable pageable) {
        return eventRepository.findByProgramId(programId, pageable)
                .map(this::toDto);
    }

    private EventDto toDto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setEventTitle(event.getTitle());
        dto.setStartTime(event.getStartTime());
        dto.setEndTime(event.getEndTime());

        if (event.getProgram() != null) {
            dto.setProgramId(event.getProgram().getId());
        }

        return dto;
    }
}

