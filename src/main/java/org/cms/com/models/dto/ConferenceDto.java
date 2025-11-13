package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ConferenceDto {

    private Long id;
    private String conferenceName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String location;
    private String logoPath;
    private String coverPath;
}

