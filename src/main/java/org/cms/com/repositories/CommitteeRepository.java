package org.cms.com.repositories;

import org.cms.com.domain.Committee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommitteeRepository extends JpaRepository<Committee, Long> {
    Page<Committee> getByConference(Long conferenceId, Pageable pageable);
    Page<Committee> getByConferenceAndType(Long conferenceId, String committeeType, Pageable pageable);
    Page<Committee> findByConferenceId(Long conferenceId, Pageable pageable);
    Page<Committee> findByConferenceIdAndCommitteeType(Long conferenceId, String committeeType, Pageable pageable);
}

