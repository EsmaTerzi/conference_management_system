package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
public class CreateEventRequest {

    private Long programId;
    private String eventTitle;
    private LocalTime startTime;
    private LocalTime endTime;
}
