package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.SponsorDto;
import org.cms.com.services.ISponsorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class SponsorController {

    private final ISponsorService ISponsorService;

    @PostMapping
    public ResponseEntity<SponsorDto> createSponsor(@RequestBody SponsorDto sponsorDto) {
        SponsorDto createdSponsor = ISponsorService.createSponsor(sponsorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSponsor);
    }

    @GetMapping
    public ResponseEntity<List<SponsorDto>> getAllSponsors() {
        List<SponsorDto> sponsors = ISponsorService.getAllSponsors();
        return ResponseEntity.ok(sponsors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorDto> getSponsorById(@PathVariable Long id) {
        SponsorDto sponsor = ISponsorService.getSponsorById(id);
        return ResponseEntity.ok(sponsor);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<SponsorDto>> getSponsorsByConferenceId(@PathVariable Long conferenceId) {
        List<SponsorDto> sponsors = ISponsorService.getByConferenceId(conferenceId);
        return ResponseEntity.ok(sponsors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SponsorDto> updateSponsor(@PathVariable Long id, @RequestBody SponsorDto sponsorDto) {
        SponsorDto updatedSponsor = ISponsorService.updateSponsor(id, sponsorDto);
        return ResponseEntity.ok(updatedSponsor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
        ISponsorService.deleteSponsor(id);
        return ResponseEntity.noContent().build();
    }
}
