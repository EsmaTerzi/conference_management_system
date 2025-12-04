package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.ProgramDto;
import org.cms.com.models.dto.CreateProgramRequest;
import org.cms.com.services.ProgramService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProgramController {

    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<ProgramDto> create(@RequestBody CreateProgramRequest request) {
        ProgramDto created = programService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramDto> update(@PathVariable Long id, @RequestBody CreateProgramRequest request) {
        ProgramDto updated = programService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        programService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDto> get(@PathVariable Long id) {
        ProgramDto program = programService.get(id);
        return ResponseEntity.ok(program);
    }

    @GetMapping
    public ResponseEntity<Page<ProgramDto>> listAll(Pageable pageable) {
        Page<ProgramDto> programs = programService.listAll(pageable);
        return ResponseEntity.ok(programs);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<ProgramDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<ProgramDto> programs = programService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(programs);
    }
}
