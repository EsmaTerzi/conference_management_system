package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.Person;
import org.cms.com.domain.PersonConference;
import org.cms.com.models.dto.CreatePersonConferenceRequest;
import org.cms.com.models.dto.PersonConferenceDto;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.repositories.PersonConferenceRepository;
import org.cms.com.repositories.PersonRepository;
import org.cms.com.services.PersonConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonConferenceServiceImpl implements PersonConferenceService {

    private final PersonConferenceRepository personConferenceRepository;
    private final PersonRepository personRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public PersonConferenceDto create(CreatePersonConferenceRequest request) {
        PersonConference personConference = new PersonConference();
        personConference.setCommittee(request.getCommittee());

        if (request.getPersonId() != null) {
            Person person = personRepository.findById(request.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Person not found"));
            personConference.setPerson(person);
        }

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            personConference.setConference(conference);
        }

        PersonConference saved = personConferenceRepository.save(personConference);
        return toDto(saved);
    }

    @Override
    public PersonConferenceDto update(Long id, CreatePersonConferenceRequest request) {
        PersonConference personConference = personConferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PersonConference not found"));

        personConference.setCommittee(request.getCommittee());

        if (request.getPersonId() != null) {
            Person person = personRepository.findById(request.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Person not found"));
            personConference.setPerson(person);
        }

        if (request.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(request.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            personConference.setConference(conference);
        }

        PersonConference updated = personConferenceRepository.save(personConference);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        personConferenceRepository.deleteById(id);
    }

    @Override
    public PersonConferenceDto get(Long id) {
        return personConferenceRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("PersonConference not found"));
    }

    @Override
    public Page<PersonConferenceDto> listAll(Pageable pageable) {
        return personConferenceRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<PersonConferenceDto> getByConferenceId(Long conferenceId, Pageable pageable) {
        return personConferenceRepository.findByConference_Id(conferenceId, pageable)
                .map(this::toDto);
    }

    @Override
    public Page<PersonConferenceDto> getByPersonId(Long personId, Pageable pageable) {
        return personConferenceRepository.findByPerson_Id(personId, pageable)
                .map(this::toDto);
    }

    private PersonConferenceDto toDto(PersonConference personConference) {
        PersonConferenceDto dto = new PersonConferenceDto();
        dto.setId(personConference.getId());
        dto.setCommittee(personConference.getCommittee());

        if (personConference.getConference() != null) {
            dto.setConferenceId(personConference.getConference().getId());
        }

        if (personConference.getPerson() != null) {
            dto.setPersonId(personConference.getPerson().getId());
            dto.setPersonName(personConference.getPerson().getName());
            dto.setPersonSurname(personConference.getPerson().getSurname());
        }

        return dto;
    }
}

