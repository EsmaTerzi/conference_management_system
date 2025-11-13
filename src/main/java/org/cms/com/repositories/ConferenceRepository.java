package org.cms.com.repositories;

import org.cms.com.domain.Conference;
import org.cms.com.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    List<Conference> findByOwner(Person owner); // admin’in oluşturduğu konferanslar
}

