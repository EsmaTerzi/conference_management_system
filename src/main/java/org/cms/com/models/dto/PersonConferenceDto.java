package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonConferenceDto {

    private Long id;
    private Long conferenceId;
    private Long personId;
    private String personName;
    private String personSurname;
}

