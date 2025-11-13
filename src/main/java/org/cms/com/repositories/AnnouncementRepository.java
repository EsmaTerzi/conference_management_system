package org.cms.com.repositories;

import org.cms.com.domain.Announcement;
import org.cms.com.domain.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findByConferenceId(Long conferenceId, Pageable pageable);
}

