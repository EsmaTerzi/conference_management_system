package org.cms.com.services;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Person;
import org.cms.com.models.dto.AuthResponse;
import org.cms.com.models.dto.LoginRequest;
import org.cms.com.models.dto.RegisterRequest;
import org.cms.com.repositories.PersonRepository;
import org.cms.com.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthResponse register(RegisterRequest request) {
        // Email kontrolü
        if (personRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Yeni kullanıcı oluştur
        Person person = new Person();
        person.setName(request.getPersonName());
        person.setSurname(request.getPersonSurname());
        person.setEmail(request.getEmail());
        person.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        person.setTitle(request.getTitle());

        Person savedPerson = personRepository.save(person);

        // JWT token oluştur
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedPerson.getEmail());
        String token = jwtService.generateToken(userDetails);

        // Response oluştur
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setPersonId(savedPerson.getId());
        response.setFullName(savedPerson.getName() + " " + savedPerson.getSurname());

        return response;
    }

    public AuthResponse login(LoginRequest request) {
        // Kullanıcı doğrulama
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Kullanıcıyı getir
        Person person = personRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // JWT token oluştur
        UserDetails userDetails = userDetailsService.loadUserByUsername(person.getEmail());
        String token = jwtService.generateToken(userDetails);

        // Response oluştur
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setPersonId(person.getId());
        response.setFullName(person.getName() + " " + person.getSurname());

        return response;
    }
}

