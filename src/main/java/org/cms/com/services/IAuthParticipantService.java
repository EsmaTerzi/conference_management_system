package org.cms.com.services;

import org.cms.com.models.dto.AuthResponse;
import org.cms.com.models.dto.ParticipantLoginRequest;
import org.cms.com.models.dto.ParticipantRegisterRequest;

public interface IAuthParticipantService {
    public AuthResponse registerParticipent(ParticipantRegisterRequest request);
    public AuthResponse loginParticipent(ParticipantLoginRequest request);
}
