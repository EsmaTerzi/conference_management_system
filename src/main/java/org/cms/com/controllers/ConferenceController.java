package org.cms.com.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.ConferenceDto;
import org.cms.com.models.dto.CreateConferenceRequest;
import org.cms.com.services.IConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ConferenceController {

    private final IConferenceService IConferenceService;

    @PostMapping
    public ResponseEntity<ConferenceDto> create(@RequestBody @Valid CreateConferenceRequest request) {
        ConferenceDto created = IConferenceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConferenceDto> update(@PathVariable Long id, @RequestBody @Valid CreateConferenceRequest request) {
        ConferenceDto updated = IConferenceService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        IConferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDto> get(@PathVariable Long id) {
        ConferenceDto conference = IConferenceService.get(id);
        return ResponseEntity.ok(conference);
    }

    @GetMapping
    public ResponseEntity<Page<ConferenceDto>> listAll(Pageable pageable) {
        Page<ConferenceDto> conferences = IConferenceService.listAll(pageable);
        return ResponseEntity.ok(conferences);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Page<ConferenceDto>> getByOwnerId(@PathVariable Long ownerId, Pageable pageable) {
        Page<ConferenceDto> conferences = IConferenceService.getByOwnerId(ownerId, pageable);
        return ResponseEntity.ok(conferences);
    }
}
