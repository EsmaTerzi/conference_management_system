package org.cms.com.repositories;

import org.cms.com.domain.Participant;
import org.cms.com.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByEmail(String email);
    Page<Participant> findAll(Pageable pageable);
    @Query("""
        SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END
        FROM Participant p
        JOIN p.conferences c
        WHERE p.id = :participantId
        AND c.id = :conferenceId
    """)
    boolean existsParticipantInConference(
            @Param("participantId") Long participantId,
            @Param("conferenceId") Long conferenceId
    );
}
