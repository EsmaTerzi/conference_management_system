package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ImportantDateDto {

    private Long id;
    private Long conferenceId;
    private String impTitle;
    private LocalDate impDate;
}
