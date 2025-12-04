package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.EventDto;
import org.cms.com.models.dto.CreateEventRequest;
import org.cms.com.services.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventDto> create(@RequestBody CreateEventRequest request) {
        EventDto created = eventService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> update(@PathVariable Long id, @RequestBody CreateEventRequest request) {
        EventDto updated = eventService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> get(@PathVariable Long id) {
        EventDto event = eventService.get(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<Page<EventDto>> listAll(Pageable pageable) {
        Page<EventDto> events = eventService.listAll(pageable);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<Page<EventDto>> getByProgramId(@PathVariable Long programId, Pageable pageable) {
        Page<EventDto> events = eventService.getByProgramId(programId, pageable);
        return ResponseEntity.ok(events);
    }
}
