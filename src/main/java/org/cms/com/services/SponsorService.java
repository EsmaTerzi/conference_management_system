package org.cms.com.services;

import org.cms.com.models.dto.SponsorDto;
import java.util.List;

public interface SponsorService {
    SponsorDto createSponsor(SponsorDto sponsorDto);
    List<SponsorDto> getAllSponsors();
    SponsorDto getSponsorById(Long id);
    SponsorDto updateSponsor(Long id, SponsorDto sponsorDto);
    void deleteSponsor(Long id);
    List<SponsorDto> getByConferenceId(Long conferenceId);
}
