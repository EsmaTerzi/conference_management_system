package org.cms.com.services.impl;

import org.cms.com.domain.Person;
import org.cms.com.repositories.PersonRepository;
import org.cms.com.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    public Optional<Person> getByEmail(String email) {
        return personRepository.findByEmail(email);
    }
}

