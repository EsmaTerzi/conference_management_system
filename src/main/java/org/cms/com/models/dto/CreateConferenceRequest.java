package org.cms.com.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.cms.com.domain.TemplateType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class CreateConferenceRequest {

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

    // Sponsors
    private List<SponsorDto> sponsors;

    // Payment bilgisi (isteğe bağlı) - conference oluştururken banka/iban/mail gibi bilgileri alır
    @Size(max = 255)
    private String paymentBankName;

    @Size(max = 255)
    private String paymentBankNameOptional;

    @Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z0-9]{11,30}$", message = "IBAN format gecersiz")
    private String paymentIban;

    @Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z0-9]{11,30}$", message = "IBAN format gecersiz")
    private String paymentIbanOptional;

    @Email(message = "Email format gecersiz")
    @Size(max = 255)
    private String paymentDocumentEmail;

    @Size(max = 5000)
    private String paymentTextArea;

    private String paymentFeeText;
}
