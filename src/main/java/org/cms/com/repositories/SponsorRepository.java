package org.cms.com.repositories;

import org.cms.com.domain.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    List<Sponsor> findByConference_Id(Long conferenceId);
    Page<Sponsor> findByConference_Id(Long conferenceId, Pageable pageable);
}
