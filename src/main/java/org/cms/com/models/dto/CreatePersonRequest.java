package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePersonRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String title;
}

