package org.cms.com.models.dto;

import lombok.Getter;
import lombok.Setter;
import org.cms.com.domain.TemplateType;

import java.time.LocalDate;

@Getter
@Setter
public class ConferenceDto {

    private Long id;
    private String conferenceName;
    private String shortSubtitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String location;
    private String logoPath;
    private String coverPath;

    // Template seçimi (CLASSIC veya MODERN)
    private TemplateType templateType;

    // Owner bilgileri
    private Long ownerId;
    private String ownerEmail;
    private String ownerName;

    private String footerOrganizationTitle;
    private String footerAddress;
    private String footerCityCountry;
    private String footerYearText;
    private String footerPhone;
    private String footerEmail;
    private String footerFacebookUrl;
    private String footerTwitterUrl;
    private String footerInstagramUrl;
    private String footerLinkedinUrl;


}
