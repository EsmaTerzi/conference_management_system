package org.cms.com.repositories;

import org.cms.com.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByProgramId(Long programId, Pageable pageable);
}
