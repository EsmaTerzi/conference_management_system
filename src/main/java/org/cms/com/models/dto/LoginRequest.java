package org.cms.com.models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "Lütfen emailinizi girin.")
    private String email;

    @NotEmpty(message = "Lütfen şifrenizi girin.")
    private String password;
}
