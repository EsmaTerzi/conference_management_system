package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AnnouncementDto {

    private Long id;
    private Long conferenceId;
    private String announcementTitle;
    private LocalDateTime announcementDate;
    private String announcementDesc;
}
