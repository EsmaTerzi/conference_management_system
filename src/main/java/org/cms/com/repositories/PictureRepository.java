package org.cms.com.repositories;

import org.cms.com.domain.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    Page<Picture> findByConferenceId(Long conferenceId, Pageable pageable);
}
