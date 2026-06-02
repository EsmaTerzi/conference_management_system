package org.cms.com.repositories;

import org.cms.com.domain.PaperSubmission;
import org.cms.com.domain.PaperSubmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperSubmissionRepository extends JpaRepository<PaperSubmission, Long> {
    List<PaperSubmission> findByParticipantId(Long participantId);
    List<PaperSubmission> findByStatus(PaperSubmissionStatus status);
}

