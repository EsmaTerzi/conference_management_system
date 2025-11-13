package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureDto {

    private Long id;
    private Long conferenceId;
    private String filePath;
    private String caption;
}
