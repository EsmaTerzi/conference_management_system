package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramDto {

    private Long id;
    private Long conferenceId;
    private String dayTabs;
    private String timeIntervals;
    private String sessionTitle;
    private String subtopics;
}
