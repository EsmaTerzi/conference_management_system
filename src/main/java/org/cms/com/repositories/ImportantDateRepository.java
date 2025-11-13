package org.cms.com.repositories;

import org.cms.com.domain.ImportantDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportantDateRepository extends JpaRepository<ImportantDate, Long> {
    Page<ImportantDate> findByConferenceId(Long conferenceId, Pageable pageable);
}
