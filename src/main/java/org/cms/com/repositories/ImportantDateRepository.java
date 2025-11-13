package org.cms.com.repositories;

import org.cms.com.domain.ImportantDate;
import org.cms.com.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImportantDateRepository extends JpaRepository<ImportantDate, Long> {
    List<ImportantDate> findByConference(Conference conference);
}

