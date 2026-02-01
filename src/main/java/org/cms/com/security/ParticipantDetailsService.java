package org.cms.com.security;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Participant;
import org.cms.com.repositories.ParticipantRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("participantDetailsService")
@RequiredArgsConstructor
public class ParticipantDetailsService implements UserDetailsService {

    private final ParticipantRepository participantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Participant participant = participantRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Participant not found: " + username));
        return new User(participant.getEmail(), participant.getPassword(), Collections.emptyList());
    }
}

