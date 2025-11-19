package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePersonConferenceRequest {

    private Long conferenceId;
    private Long personId;
    private String committee;
}

