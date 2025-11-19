package org.cms.com.repositories;

import org.cms.com.domain.PersonConference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonConferenceRepository extends JpaRepository<PersonConference, Long> {
    Page<PersonConference> findByConference_Id(Long conferenceId, Pageable pageable);
    Page<PersonConference> findByPerson_Id(Long personId, Pageable pageable);
}
