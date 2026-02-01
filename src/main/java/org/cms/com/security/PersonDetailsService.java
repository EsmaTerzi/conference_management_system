package org.cms.com.security;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Person;
import org.cms.com.repositories.PersonRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Primary
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        // Not: password alanınız "passwordHash" olarak isimlendirilmiş
        return new User(person.getEmail(), person.getPasswordHash(), Collections.emptyList());
    }
}