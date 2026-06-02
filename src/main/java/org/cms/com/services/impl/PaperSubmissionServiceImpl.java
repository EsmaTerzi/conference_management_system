package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.PaperSubmission;
import org.cms.com.domain.PaperSubmissionStatus;
import org.cms.com.domain.Participant;
import org.cms.com.models.dto.PaperSubmissionCreateRequest;
import org.cms.com.models.dto.PaperSubmissionResponse;
import org.cms.com.models.dto.PaperSubmissionReviewRequest;
import org.cms.com.repositories.PaperSubmissionRepository;
import org.cms.com.repositories.ParticipantRepository;
import org.cms.com.services.IPaperSubmissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaperSubmissionServiceImpl implements IPaperSubmissionService {

    private final PaperSubmissionRepository paperSubmissionRepository;
    private final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public PaperSubmissionResponse submitPaper(String participantEmail, PaperSubmissionCreateRequest request) {
        Participant participant = participantRepository.findByEmail(participantEmail)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        PaperSubmission paperSubmission = new PaperSubmission();
        paperSubmission.setTitle(request.getTitle());
        paperSubmission.setAbstractText(request.getAbstractText());
        paperSubmission.setKeywords(request.getKeywords());
        paperSubmission.setFilePath(request.getFilePath());
        paperSubmission.setStatus(PaperSubmissionStatus.PENDING);
        paperSubmission.setSubmittedAt(LocalDateTime.now());
        paperSubmission.setParticipant(participant);

        PaperSubmission savedPaper = paperSubmissionRepository.save(paperSubmission);
        return mapToResponse(savedPaper);
    }

    @Override
    public List<PaperSubmissionResponse> getMySubmissions(String participantEmail) {
        Participant participant = participantRepository.findByEmail(participantEmail)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        
        return paperSubmissionRepository.findByParticipantId(participant.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaperSubmissionResponse> getAllSubmissions() {
        return paperSubmissionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaperSubmissionResponse> getPendingSubmissions() {
        return paperSubmissionRepository.findByStatus(PaperSubmissionStatus.PENDING)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaperSubmissionResponse reviewPaper(Long id, PaperSubmissionReviewRequest request) {
        PaperSubmission paperSubmission = paperSubmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paper submission not found"));

        paperSubmission.setStatus(request.getStatus());
        paperSubmission.setAdminNote(request.getAdminNote());
        paperSubmission.setReviewedAt(LocalDateTime.now());

        PaperSubmission updatedPaper = paperSubmissionRepository.save(paperSubmission);
        return mapToResponse(updatedPaper);
    }

    private PaperSubmissionResponse mapToResponse(PaperSubmission paper) {
        return PaperSubmissionResponse.builder()
                .id(paper.getId())
                .title(paper.getTitle())
                .abstractText(paper.getAbstractText())
                .keywords(paper.getKeywords())
                .filePath(paper.getFilePath())
                .status(paper.getStatus())
                .submittedAt(paper.getSubmittedAt())
                .reviewedAt(paper.getReviewedAt())
                .adminNote(paper.getAdminNote())
                .participantId(paper.getParticipant().getId())
                .participantName(paper.getParticipant().getName() + " " + paper.getParticipant().getSurname())
                .build();
    }
}

