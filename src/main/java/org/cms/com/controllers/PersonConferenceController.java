package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.PersonConferenceDto;
import org.cms.com.models.dto.CreatePersonConferenceRequest;
import org.cms.com.services.IPersonConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person-conferences")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class PersonConferenceController {

    private final IPersonConferenceService IPersonConferenceService;

    @PostMapping
    public ResponseEntity<PersonConferenceDto> create(@RequestBody CreatePersonConferenceRequest request) {
        PersonConferenceDto created = IPersonConferenceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonConferenceDto> update(@PathVariable Long id, @RequestBody CreatePersonConferenceRequest request) {
        PersonConferenceDto updated = IPersonConferenceService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        IPersonConferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonConferenceDto> get(@PathVariable Long id) {
        PersonConferenceDto personConference = IPersonConferenceService.get(id);
        return ResponseEntity.ok(personConference);
    }

    @GetMapping
    public ResponseEntity<Page<PersonConferenceDto>> listAll(Pageable pageable) {
        Page<PersonConferenceDto> personConferences = IPersonConferenceService.listAll(pageable);
        return ResponseEntity.ok(personConferences);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<PersonConferenceDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<PersonConferenceDto> personConferences = IPersonConferenceService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(personConferences);
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<Page<PersonConferenceDto>> getByPersonId(@PathVariable Long personId, Pageable pageable) {
        Page<PersonConferenceDto> personConferences = IPersonConferenceService.getByPersonId(personId, pageable);
        return ResponseEntity.ok(personConferences);
    }
}
