package org.cms.com.services;

import org.cms.com.models.dto.*;

public interface IAuthParticipantService {
    public AuthResponse registerParticipant(ParticipantRegisterRequest request);
    public AuthResponse loginParticipant(ParticipantLoginRequest request);
    public ProfileResponse getProfile(String email);
    public void updatePassword(String email, UpdatePasswordRequest request);
}
