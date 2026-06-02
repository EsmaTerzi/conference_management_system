package org.cms.com.services;

import org.cms.com.models.dto.PaperSubmissionCreateRequest;
import org.cms.com.models.dto.PaperSubmissionResponse;
import org.cms.com.models.dto.PaperSubmissionReviewRequest;

import java.util.List;

public interface IPaperSubmissionService {
    PaperSubmissionResponse submitPaper(String participantEmail, PaperSubmissionCreateRequest request);
    List<PaperSubmissionResponse> getMySubmissions(String participantEmail);
    List<PaperSubmissionResponse> getAllSubmissions();
    List<PaperSubmissionResponse> getPendingSubmissions();
    PaperSubmissionResponse reviewPaper(Long id, PaperSubmissionReviewRequest request);
}

