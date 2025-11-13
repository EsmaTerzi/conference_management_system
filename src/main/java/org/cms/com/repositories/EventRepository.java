package org.cms.com.repositories;

import org.cms.com.domain.Event;
import org.cms.com.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByProgram(Program program);
}

