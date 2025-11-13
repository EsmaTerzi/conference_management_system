package org.cms.com.repositories;

import org.cms.com.domain.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Page<Program> findByConferenceId(Long conferenceId, Pageable pageable);
}
