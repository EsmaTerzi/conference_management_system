package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.PersonConferenceDto;
import org.cms.com.models.dto.CreatePersonConferenceRequest;
import org.cms.com.services.PersonConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person-conferences")
@RequiredArgsConstructor
public class PersonConferenceController {

    private final PersonConferenceService personConferenceService;

    @PostMapping
    public ResponseEntity<PersonConferenceDto> create(@RequestBody CreatePersonConferenceRequest request) {
        PersonConferenceDto created = personConferenceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonConferenceDto> update(@PathVariable Long id, @RequestBody CreatePersonConferenceRequest request) {
        PersonConferenceDto updated = personConferenceService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personConferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonConferenceDto> get(@PathVariable Long id) {
        PersonConferenceDto personConference = personConferenceService.get(id);
        return ResponseEntity.ok(personConference);
    }

    @GetMapping
    public ResponseEntity<Page<PersonConferenceDto>> listAll(Pageable pageable) {
        Page<PersonConferenceDto> personConferences = personConferenceService.listAll(pageable);
        return ResponseEntity.ok(personConferences);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<PersonConferenceDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<PersonConferenceDto> personConferences = personConferenceService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(personConferences);
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<Page<PersonConferenceDto>> getByPersonId(@PathVariable Long personId, Pageable pageable) {
        Page<PersonConferenceDto> personConferences = personConferenceService.getByPersonId(personId, pageable);
        return ResponseEntity.ok(personConferences);
    }
}

