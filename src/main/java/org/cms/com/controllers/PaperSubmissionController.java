package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.PaperSubmissionCreateRequest;
import org.cms.com.models.dto.PaperSubmissionResponse;
import org.cms.com.models.dto.PaperSubmissionReviewRequest;
import org.cms.com.services.IPaperSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaperSubmissionController {

    private final IPaperSubmissionService paperSubmissionService;

    // Participant Endpoints
    @PostMapping("/paper-submissions")
    public ResponseEntity<PaperSubmissionResponse> submitPaper(@RequestBody PaperSubmissionCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(paperSubmissionService.submitPaper(email, request));
    }

    @GetMapping("/paper-submissions/my")
    public ResponseEntity<List<PaperSubmissionResponse>> getMySubmissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(paperSubmissionService.getMySubmissions(email));
    }

    // Admin Endpoints
    @GetMapping("/admin/paper-submissions")
    public ResponseEntity<List<PaperSubmissionResponse>> getAllSubmissions() {
        return ResponseEntity.ok(paperSubmissionService.getAllSubmissions());
    }

    @GetMapping("/admin/paper-submissions/pending")
    public ResponseEntity<List<PaperSubmissionResponse>> getPendingSubmissions() {
        return ResponseEntity.ok(paperSubmissionService.getPendingSubmissions());
    }

    @PutMapping("/admin/paper-submissions/{id}/review")
    public ResponseEntity<PaperSubmissionResponse> reviewPaper(
            @PathVariable Long id,
            @RequestBody PaperSubmissionReviewRequest request) {
        return ResponseEntity.ok(paperSubmissionService.reviewPaper(id, request));
    }
}

