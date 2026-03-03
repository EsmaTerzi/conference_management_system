package org.cms.com.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cms.com.domain.DocumentStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private Long id;
    private String name;
    private String surname;
    private String organisation;
    private String email;
    private String title;
    private String typeOfParticipation;
    private String password;
    private DocumentStatus documentStatus;
}
