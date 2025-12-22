package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.SponsorDto;
import org.cms.com.services.SponsorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
@RequiredArgsConstructor
public class SponsorController {

    private final SponsorService sponsorService;

    @PostMapping
    public ResponseEntity<SponsorDto> createSponsor(@RequestBody SponsorDto sponsorDto) {
        SponsorDto createdSponsor = sponsorService.createSponsor(sponsorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSponsor);
    }

    @GetMapping
    public ResponseEntity<List<SponsorDto>> getAllSponsors() {
        List<SponsorDto> sponsors = sponsorService.getAllSponsors();
        return ResponseEntity.ok(sponsors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorDto> getSponsorById(@PathVariable Long id) {
        SponsorDto sponsor = sponsorService.getSponsorById(id);
        return ResponseEntity.ok(sponsor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SponsorDto> updateSponsor(@PathVariable Long id, @RequestBody SponsorDto sponsorDto) {
        SponsorDto updatedSponsor = sponsorService.updateSponsor(id, sponsorDto);
        return ResponseEntity.ok(updatedSponsor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
        sponsorService.deleteSponsor(id);
        return ResponseEntity.noContent().build();
    }
}
