package org.cms.com.models.dto;

import lombok.Data;
import org.cms.com.domain.PaperSubmissionStatus;

@Data
public class PaperSubmissionReviewRequest {
    private PaperSubmissionStatus status;
    private String adminNote;
}

