
package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.ConferenceDto;
import org.cms.com.models.dto.CreateConferenceRequest;
import org.cms.com.services.ConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceService conferenceService;

    @PostMapping
    public ResponseEntity<ConferenceDto> create(@RequestBody CreateConferenceRequest request) {
        ConferenceDto created = conferenceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConferenceDto> update(@PathVariable Long id, @RequestBody CreateConferenceRequest request) {
        ConferenceDto updated = conferenceService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        conferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDto> get(@PathVariable Long id) {
        ConferenceDto conference = conferenceService.get(id);
        return ResponseEntity.ok(conference);
    }

    @GetMapping
    public ResponseEntity<Page<ConferenceDto>> listAll(Pageable pageable) {
        Page<ConferenceDto> conferences = conferenceService.listAll(pageable);
        return ResponseEntity.ok(conferences);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Page<ConferenceDto>> getByOwnerId(@PathVariable Long ownerId, Pageable pageable) {
        Page<ConferenceDto> conferences = conferenceService.getByOwnerId(ownerId, pageable);
        return ResponseEntity.ok(conferences);
    }
}

