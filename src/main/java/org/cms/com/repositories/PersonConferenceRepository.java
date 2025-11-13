package org.cms.com.repositories;


import org.cms.com.domain.PersonConference;
import org.cms.com.domain.Person;
import  org.cms.com.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonConferenceRepository extends JpaRepository<PersonConference, Long> {
    List<PersonConference> findByConference(Conference conference);
    List<PersonConference> findByPerson(Person person);
}

