package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.ImportantDateDto;
import org.cms.com.models.dto.CreateImportantDateRequest;
import org.cms.com.services.IImportantDateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/important-dates")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ImportantDateController {

    private final IImportantDateService IImportantDateService;

    @PostMapping
    public ResponseEntity<ImportantDateDto> create(@RequestBody CreateImportantDateRequest request) {
        ImportantDateDto created = IImportantDateService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImportantDateDto> update(@PathVariable Long id, @RequestBody CreateImportantDateRequest request) {
        ImportantDateDto updated = IImportantDateService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        IImportantDateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImportantDateDto> get(@PathVariable Long id) {
        ImportantDateDto importantDate = IImportantDateService.get(id);
        return ResponseEntity.ok(importantDate);
    }

    @GetMapping
    public ResponseEntity<Page<ImportantDateDto>> listAll(Pageable pageable) {
        Page<ImportantDateDto> importantDates = IImportantDateService.listAll(pageable);
        return ResponseEntity.ok(importantDates);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<ImportantDateDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<ImportantDateDto> importantDates = IImportantDateService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(importantDates);
    }
}
