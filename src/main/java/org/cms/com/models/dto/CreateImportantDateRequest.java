package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateImportantDateRequest {

    private Long conferenceId;
    private String impTitle;   // "Bildiri Son Gönderim"
    private LocalDate impDate;
}
