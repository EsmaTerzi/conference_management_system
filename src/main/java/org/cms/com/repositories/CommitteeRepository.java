package org.cms.com.repositories;

import org.cms.com.domain.Committee;
import org.cms.com.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommitteeRepository extends JpaRepository<Committee, Long> {
    List<Committee> findByConference(Conference conference);
    List<Committee> findByConferenceAndCommitteeType(Conference conference, String committeeType);
}

