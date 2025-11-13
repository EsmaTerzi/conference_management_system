package org.cms.com.repositories;

import org.cms.com.domain.Announcement;
import org.cms.com.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByConference(Conference conference);
}

