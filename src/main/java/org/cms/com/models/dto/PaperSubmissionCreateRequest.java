package org.cms.com.models.dto;

import lombok.Data;

@Data
public class PaperSubmissionCreateRequest {
    private String title;
    private String abstractText;
    private String keywords;
    private String filePath;
}

