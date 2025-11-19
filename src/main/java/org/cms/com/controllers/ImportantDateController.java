package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.ImportantDateDto;
import org.cms.com.models.dto.CreateImportantDateRequest;
import org.cms.com.services.ImportantDateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/important-dates")
@RequiredArgsConstructor
public class ImportantDateController {

    private final ImportantDateService importantDateService;

    @PostMapping
    public ResponseEntity<ImportantDateDto> create(@RequestBody CreateImportantDateRequest request) {
        ImportantDateDto created = importantDateService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImportantDateDto> update(@PathVariable Long id, @RequestBody CreateImportantDateRequest request) {
        ImportantDateDto updated = importantDateService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        importantDateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImportantDateDto> get(@PathVariable Long id) {
        ImportantDateDto importantDate = importantDateService.get(id);
        return ResponseEntity.ok(importantDate);
    }

    @GetMapping
    public ResponseEntity<Page<ImportantDateDto>> listAll(Pageable pageable) {
        Page<ImportantDateDto> importantDates = importantDateService.listAll(pageable);
        return ResponseEntity.ok(importantDates);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<ImportantDateDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<ImportantDateDto> importantDates = importantDateService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(importantDates);
    }
}

