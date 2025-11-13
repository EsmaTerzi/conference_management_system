package org.cms.com.repositories;

import org.cms.com.domain.Picture;
import org.cms.com.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findByConference(Conference conference);
}

