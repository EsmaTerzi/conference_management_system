package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.CommitteeDto;
import org.cms.com.models.dto.CreateCommitteeRequest;
import org.cms.com.services.CommitteeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/committees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CommitteeController {

    private final CommitteeService committeeService;

    @PostMapping
    public ResponseEntity<CommitteeDto> create(@RequestBody CreateCommitteeRequest request) {
        CommitteeDto created = committeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommitteeDto> update(@PathVariable Long id, @RequestBody CreateCommitteeRequest request) {
        CommitteeDto updated = committeeService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        committeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommitteeDto> get(@PathVariable Long id) {
        CommitteeDto committee = committeeService.get(id);
        return ResponseEntity.ok(committee);
    }

    @GetMapping
    public ResponseEntity<Page<CommitteeDto>> listAll(Pageable pageable) {
        Page<CommitteeDto> committees = committeeService.listAll(pageable);
        return ResponseEntity.ok(committees);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<CommitteeDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<CommitteeDto> committees = committeeService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(committees);
    }

    @GetMapping("/conference/{conferenceId}/type/{committeeType}")
    public ResponseEntity<Page<CommitteeDto>> getByConferenceIdAndType(
            @PathVariable Long conferenceId,
            @PathVariable String committeeType,
            Pageable pageable) {
        Page<CommitteeDto> committees = committeeService.getByConferenceIdAndType(conferenceId, committeeType, pageable);
        return ResponseEntity.ok(committees);
    }
}
