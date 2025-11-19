package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.PersonDto;
import org.cms.com.models.dto.CreatePersonRequest;
import org.cms.com.services.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody CreatePersonRequest request) {
        PersonDto created = personService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody CreatePersonRequest request) {
        PersonDto updated = personService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> get(@PathVariable Long id) {
        PersonDto person = personService.get(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<Page<PersonDto>> listAll(Pageable pageable) {
        Page<PersonDto> persons = personService.listAll(pageable);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PersonDto> findByEmail(@PathVariable String email) {
        return personService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

