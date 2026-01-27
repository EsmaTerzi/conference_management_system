package org.cms.com.services;

import org.cms.com.models.dto.*;
import org.cms.com.services.impl.AuthServiceImpl;
import org.cms.com.services.impl.ConferenceServiceImpl;

public interface IAuthService {
    public AuthResponse register(RegisterRequest request);
    public AuthResponse login(LoginRequest request);
    public ProfileResponse getProfile(String email);
    public void updatePassword(String email, UpdatePasswordRequest request);

}
