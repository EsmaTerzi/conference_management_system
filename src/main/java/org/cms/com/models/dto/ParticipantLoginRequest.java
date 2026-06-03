package org.cms.com.models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantLoginRequest {

        @NotEmpty(message = "Lütfen emailinizi girin.")
        private String email;

        @NotEmpty(message = "Lütfen şifrenizi girin.")
        private String password;

        private Long conferenceId;

}
