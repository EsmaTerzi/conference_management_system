package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.Sponsor;
import org.cms.com.models.dto.SponsorDto;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.repositories.SponsorRepository;
import org.cms.com.services.SponsorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public SponsorDto createSponsor(SponsorDto sponsorDto) {
        System.out.println("Creating sponsor with conferenceId: " + sponsorDto.getConferenceId());

        Sponsor sponsor = new Sponsor();
        sponsor.setName(sponsorDto.getName());
        sponsor.setType(sponsorDto.getType());
        sponsor.setLogoUrl(sponsorDto.getLogoUrl());

        if (sponsorDto.getConferenceId() != null) {
            System.out.println("ConferenceId is not null, fetching conference...");
            Conference conference = conferenceRepository.findById(sponsorDto.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found with id: " + sponsorDto.getConferenceId()));
            sponsor.setConference(conference);
            System.out.println("Conference set successfully: " + conference.getId());
        } else {
            System.out.println("WARNING: ConferenceId is NULL!");
        }

        Sponsor savedSponsor = sponsorRepository.save(sponsor);
        System.out.println("Sponsor saved with conference_id: " +
            (savedSponsor.getConference() != null ? savedSponsor.getConference().getId() : "NULL"));
        return toDto(savedSponsor);
    }

    @Override
    public List<SponsorDto> getAllSponsors() {
        return sponsorRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SponsorDto getSponsorById(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sponsor not found"));
        return toDto(sponsor);
    }

    @Override
    public SponsorDto updateSponsor(Long id, SponsorDto sponsorDto) {
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sponsor not found"));

        if (sponsorDto.getName() != null) {
            sponsor.setName(sponsorDto.getName());
        }
        if (sponsorDto.getType() != null) {
            sponsor.setType(sponsorDto.getType());
        }
        if (sponsorDto.getLogoUrl() != null) {
            sponsor.setLogoUrl(sponsorDto.getLogoUrl());
        }
        if (sponsorDto.getConferenceId() != null) {
            Conference conference = conferenceRepository.findById(sponsorDto.getConferenceId())
                    .orElseThrow(() -> new RuntimeException("Conference not found"));
            sponsor.setConference(conference);
        }

        Sponsor updatedSponsor = sponsorRepository.save(sponsor);
        return toDto(updatedSponsor);
    }

    @Override
    public void deleteSponsor(Long id) {
        sponsorRepository.deleteById(id);
    }

    @Override
    public List<SponsorDto> getByConferenceId(Long conferenceId) {
        return sponsorRepository.findByConference_Id(conferenceId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private SponsorDto toDto(Sponsor sponsor) {
        SponsorDto dto = new SponsorDto();
        dto.setId(sponsor.getId());
        dto.setName(sponsor.getName());
        dto.setType(sponsor.getType());
        dto.setLogoUrl(sponsor.getLogoUrl());
        if (sponsor.getConference() != null) {
            dto.setConferenceId(sponsor.getConference().getId());
        }
        return dto;
    }
}
