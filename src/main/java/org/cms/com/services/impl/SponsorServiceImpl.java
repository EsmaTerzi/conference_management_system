package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Sponsor;
import org.cms.com.models.dto.SponsorDto;
import org.cms.com.repositories.SponsorRepository;
import org.cms.com.services.SponsorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;

    @Override
    public SponsorDto createSponsor(SponsorDto sponsorDto) {
        Sponsor sponsor = new Sponsor();
        sponsor.setName(sponsorDto.getName());
        sponsor.setType(sponsorDto.getType());
        sponsor.setLogoUrl(sponsorDto.getLogoUrl());
        Sponsor savedSponsor = sponsorRepository.save(sponsor);
        return mapToDto(savedSponsor);
    }

    @Override
    public SponsorDto getSponsorById(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sponsor not found"));
        return mapToDto(sponsor);
    }

    @Override
    public List<SponsorDto> getAllSponsors() {
        return sponsorRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public SponsorDto updateSponsor(Long id, SponsorDto sponsorDto) {
        Sponsor sponsor = sponsorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sponsor not found"));
        sponsor.setName(sponsorDto.getName());
        sponsor.setType(sponsorDto.getType());
        sponsor.setLogoUrl(sponsorDto.getLogoUrl());
        Sponsor updatedSponsor = sponsorRepository.save(sponsor);
        return mapToDto(updatedSponsor);
    }

    @Override
    public void deleteSponsor(Long id) {
        sponsorRepository.deleteById(id);
    }

    private SponsorDto mapToDto(Sponsor sponsor) {
        return new SponsorDto(sponsor.getId(), sponsor.getName(), sponsor.getType(), sponsor.getLogoUrl());
    }
}
