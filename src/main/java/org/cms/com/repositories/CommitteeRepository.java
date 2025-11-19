package org.cms.com.repositories;

import org.cms.com.domain.Committee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitteeRepository extends JpaRepository<Committee, Long> {
    Page<Committee> findByConference_Id(Long conferenceId, Pageable pageable);
    Page<Committee> findByConference_IdAndCommitteeType(Long conferenceId, String committeeType, Pageable pageable);
}

