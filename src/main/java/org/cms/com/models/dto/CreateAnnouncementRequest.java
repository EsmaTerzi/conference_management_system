package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAnnouncementRequest {

    private Long conferenceId;
    private String announcementTitle;
    private LocalDateTime announcementDate;   // sadece tarih istiyorsan LocalDate de olabilir
    private String announcementDesc;
}
