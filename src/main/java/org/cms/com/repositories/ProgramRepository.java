package org.cms.com.repositories;

import org.cms.com.domain.Program;
import org.cms.com.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByConference(Conference conference);
}

