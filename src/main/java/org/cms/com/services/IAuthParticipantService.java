package org.cms.com.services;

import org.cms.com.models.dto.AuthResponse;
import org.cms.com.models.dto.ParticipantLoginRequest;
import org.cms.com.models.dto.ParticipantRegisterRequest;
import org.cms.com.models.dto.ProfileResponse;

public interface IAuthParticipantService {
    public AuthResponse registerParticipant(ParticipantRegisterRequest request);
    public AuthResponse loginParticipant(ParticipantLoginRequest request);
    public ProfileResponse getProfile(String email);
}
