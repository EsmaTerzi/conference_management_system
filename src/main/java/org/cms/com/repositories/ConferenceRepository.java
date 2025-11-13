package org.cms.com.repositories;

import org.cms.com.domain.Conference;
import org.cms.com.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    Page<Conference> findByOwner(Person owner, Pageable pageable);
    Page<Conference> findByOwnerId(Long ownerId, Pageable pageable);
}