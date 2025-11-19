package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.AnnouncementDto;
import org.cms.com.models.dto.CreateAnnouncementRequest;
import org.cms.com.services.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<AnnouncementDto> create(@RequestBody CreateAnnouncementRequest request) {
        AnnouncementDto created = announcementService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementDto> update(@PathVariable Long id, @RequestBody CreateAnnouncementRequest request) {
        AnnouncementDto updated = announcementService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> get(@PathVariable Long id) {
        AnnouncementDto announcement = announcementService.get(id);
        return ResponseEntity.ok(announcement);
    }

    @GetMapping
    public ResponseEntity<Page<AnnouncementDto>> listAll(Pageable pageable) {
        Page<AnnouncementDto> announcements = announcementService.listAll(pageable);
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<AnnouncementDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<AnnouncementDto> announcements = announcementService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(announcements);
    }
}

