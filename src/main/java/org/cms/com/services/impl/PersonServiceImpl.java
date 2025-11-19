package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Person;
import org.cms.com.models.dto.CreatePersonRequest;
import org.cms.com.models.dto.PersonDto;
import org.cms.com.repositories.PersonRepository;
import org.cms.com.services.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PersonDto create(CreatePersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        person.setSurname(request.getSurname());
        person.setEmail(request.getEmail());
        person.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        person.setTitle(request.getTitle());

        Person saved = personRepository.save(person);
        return toDto(saved);
    }

    @Override
    public PersonDto update(Long id, CreatePersonRequest request) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        person.setName(request.getName());
        person.setSurname(request.getSurname());
        person.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            person.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        person.setTitle(request.getTitle());

        Person updated = personRepository.save(person);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public PersonDto get(Long id) {
        return personRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @Override
    public Page<PersonDto> listAll(Pageable pageable) {
        return personRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Optional<PersonDto> findByEmail(String email) {
        return personRepository.findByEmail(email)
                .map(this::toDto);
    }

    private PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setEmail(person.getEmail());
        dto.setTitle(person.getTitle());
        return dto;
    }
}

