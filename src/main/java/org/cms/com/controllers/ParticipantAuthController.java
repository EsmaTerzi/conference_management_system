package org.cms.com.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.AuthResponse;
import org.cms.com.models.dto.ParticipantRegisterRequest;
import org.cms.com.models.dto.RegisterRequest;
import org.cms.com.services.impl.AuthParticipateServiceImpl;
import org.cms.com.services.impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/participant")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowCredentials = "true")
public class ParticipantAuthController {

    private final AuthParticipateServiceImpl authParticipateService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid ParticipantRegisterRequest request) {
        try {
            AuthResponse response = authParticipateService.registerParticipent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

}
