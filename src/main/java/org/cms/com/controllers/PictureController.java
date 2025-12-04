package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.PictureDto;
import org.cms.com.models.dto.CreatePictureRequest;
import org.cms.com.services.PictureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class PictureController {

    private final PictureService pictureService;

    @PostMapping
    public ResponseEntity<PictureDto> create(@RequestBody CreatePictureRequest request) {
        PictureDto created = pictureService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PictureDto> update(@PathVariable Long id, @RequestBody CreatePictureRequest request) {
        PictureDto updated = pictureService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pictureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureDto> get(@PathVariable Long id) {
        PictureDto picture = pictureService.get(id);
        return ResponseEntity.ok(picture);
    }

    @GetMapping
    public ResponseEntity<Page<PictureDto>> listAll(Pageable pageable) {
        Page<PictureDto> pictures = pictureService.listAll(pageable);
        return ResponseEntity.ok(pictures);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Page<PictureDto>> getByConferenceId(@PathVariable Long conferenceId, Pageable pageable) {
        Page<PictureDto> pictures = pictureService.getByConferenceId(conferenceId, pageable);
        return ResponseEntity.ok(pictures);
    }
}
