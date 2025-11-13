package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.Program;
import org.cms.com.models.dto.CreateProgramRequest;
import org.cms.com.models.dto.ProgramDto;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.repositories.ProgramRepository;
import org.cms.com.services.ProgramService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public ProgramDto create(CreateProgramRequest request) {
        Program program = new Program();
        program.setDayTabs(request.getDayTabs());
        program.setTimeIntervals(request.getTimeIntervals());
        program.setSessionTitle(request.getSessionTitle());
        program.setSubtopics(request.getSubtopics());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            program.setConference(conference);
        }

        Program saved = programRepository.save(program);
        return toDto(saved);
    }

    @Override
    public ProgramDto update(Long id, CreateProgramRequest request) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        program.setDayTabs(request.getDayTabs());
        program.setTimeIntervals(request.getTimeIntervals());
        program.setSessionTitle(request.getSessionTitle());
        program.setSubtopics(request.getSubtopics());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            program.setConference(conference);
        }

        Program updated = programRepository.save(program);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        programRepository.deleteById(id);
    }

    @Override
    public ProgramDto get(Long id) {
        return programRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @Override
    public Page<ProgramDto> listAll(Pageable pageable) {
        return programRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<ProgramDto> getByConferenceId(Long conferenceId, Pageable pageable) {
        return programRepository.findByConferenceId(conferenceId, pageable)
                .map(this::toDto);
    }

    private ProgramDto toDto(Program program) {
        ProgramDto dto = new ProgramDto();
        dto.setId(program.getId());
        dto.setDayTabs(program.getDayTabs());
        dto.setTimeIntervals(program.getTimeIntervals());
        dto.setSessionTitle(program.getSessionTitle());
        dto.setSubtopics(program.getSubtopics());

        if (program.getConference() != null) {
            dto.setConferenceId(program.getConference().getId());
        }

        return dto;
    }
}

