package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProgramRequest {

    private Long conferenceId;
    private String dayTabs;        // "Day 1", "17 Eylül" vb.
    private String timeIntervals;  // "09:00-10:00"
    private String sessionTitle;
    private String subtopics;
}
