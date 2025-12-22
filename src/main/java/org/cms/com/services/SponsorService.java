package org.cms.com.services;

import org.cms.com.models.dto.SponsorDto;
import java.util.List;

public interface SponsorService {
    SponsorDto createSponsor(SponsorDto sponsorDto);
    SponsorDto getSponsorById(Long id);
    List<SponsorDto> getAllSponsors();
    SponsorDto updateSponsor(Long id, SponsorDto sponsorDto);
    void deleteSponsor(Long id);
}
