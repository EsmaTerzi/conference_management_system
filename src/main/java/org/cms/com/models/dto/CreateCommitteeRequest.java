package org.cms.com.models.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommitteeRequest {

    private Long conferenceId;
    private String committeeType;   // BILIM / DUZENLEME / ORGANIZASYON
    private String memberName;
    private String affiliation;     // Biruni Üniversitesi vb.
}

