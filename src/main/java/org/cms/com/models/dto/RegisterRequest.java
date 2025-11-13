package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String personName;
    private String personSurname;
    private String email;
    private String password;
    private String title;    // opsiyonel
}
