package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommitteeDto {

    private Long id;
    private Long conferenceId;
    private String committeeType;
    private String memberName;
    private String affiliation;
}

