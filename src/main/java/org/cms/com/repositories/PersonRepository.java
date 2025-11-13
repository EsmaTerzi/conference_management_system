package org.cms.com.repositories;

import org.cms.com.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email); // login işlemleri için
}

