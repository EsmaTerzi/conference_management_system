package org.cms.com.services.impl;

import org.cms.com.domain.PersonConference;
import org.cms.com.repositories.PersonConferenceRepository;
import org.cms.com.services.PersonConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonConferenceServiceImpl implements PersonConferenceService {

    private final PersonConferenceRepository personConferenceRepository;

    @Autowired
    public PersonConferenceServiceImpl(PersonConferenceRepository personConferenceRepository) {
        this.personConferenceRepository = personConferenceRepository;
    }

    @Override
    public Page<PersonConference> getByConferenceId(Long conferenceId, Pageable pageable) {
        return personConferenceRepository.findByConferenceId(conferenceId, pageable);
    }

    @Override
    public Page<PersonConference> getByPersonId(Long personId, Pageable pageable) {
        return personConferenceRepository.findByPersonId(personId, pageable);
    }
}

