package org.cms.com.services;

import org.cms.com.models.dto.PersonDto;
import org.cms.com.models.dto.CreatePersonRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PersonService {
    PersonDto create(CreatePersonRequest request);
    PersonDto update(Long id, CreatePersonRequest request);
    void delete(Long id);
    PersonDto get(Long id);
    Page<PersonDto> listAll(Pageable pageable);
    Optional<PersonDto> findByEmail(String email);
}

