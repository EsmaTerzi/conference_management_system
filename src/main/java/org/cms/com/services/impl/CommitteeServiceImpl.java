package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Committee;
import org.cms.com.domain.Conference;
import org.cms.com.models.dto.CommitteeDto;
import org.cms.com.models.dto.CreateCommitteeRequest;
import org.cms.com.repositories.CommitteeRepository;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.services.CommitteeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitteeServiceImpl implements CommitteeService {

    private final CommitteeRepository committeeRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public CommitteeDto create(CreateCommitteeRequest request) {
        Committee committee = new Committee();
        committee.setCommitteeType(request.getCommitteeType());
        committee.setMemberName(request.getMemberName());
        committee.setAffiliation(request.getAffiliation());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            committee.setConference(conference);
        }

        Committee saved = committeeRepository.save(committee);
        return toDto(saved);
    }

    @Override
    public CommitteeDto update(Long id, CreateCommitteeRequest request) {
        Committee committee = committeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Committee not found"));

        committee.setCommitteeType(request.getCommitteeType());
        committee.setMemberName(request.getMemberName());
        committee.setAffiliation(request.getAffiliation());

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            committee.setConference(conference);
        }

        Committee updated = committeeRepository.save(committee);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        committeeRepository.deleteById(id);
    }

    @Override
    public CommitteeDto get(Long id) {
        return committeeRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Committee not found"));
    }

    @Override
    public Page<CommitteeDto> listAll(Pageable pageable) {
        return committeeRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<CommitteeDto> getByConferenceId(Long conferenceId, Pageable pageable) {
        return committeeRepository.findByConferenceId(conferenceId, pageable)
                .map(this::toDto);
    }

    @Override
    public Page<CommitteeDto> getByConferenceIdAndType(Long conferenceId, String committeeType, Pageable pageable) {
        return committeeRepository.findByConferenceIdAndCommitteeType(conferenceId, committeeType, pageable)
                .map(this::toDto);
    }

    private CommitteeDto toDto(Committee committee) {
        CommitteeDto dto = new CommitteeDto();
        dto.setId(committee.getId());
        dto.setCommitteeType(committee.getCommitteeType());
        dto.setMemberName(committee.getMemberName());
        dto.setAffiliation(committee.getAffiliation());

        if (committee.getConference() != null) {
            dto.setConferenceId(committee.getConference().getId());
        }

        return dto;
    }
}
