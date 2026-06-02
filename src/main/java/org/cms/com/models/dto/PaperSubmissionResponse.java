package org.cms.com.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cms.com.domain.PaperSubmissionStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaperSubmissionResponse {
    private Long id;
    private String title;
    private String abstractText;
    private String keywords;
    private String filePath;
    private PaperSubmissionStatus status;
    private LocalDateTime submittedAt;
    private LocalDateTime reviewedAt;
    private String adminNote;
    private Long participantId;
    private String participantName;
}

