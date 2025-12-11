package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "conference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")
    private Long id;

    @Column(name = "conference_name", nullable = true, length = 200)
    private String conferenceName;

    @Column(name = "short_subtitle", length = 255)
    private String shortSubtitle;

    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "logo_path", length = 255)
    private String logoPath;

    @Column(name = "cover_path", length = 255)
    private String coverPath;

    // konferansı oluşturan admin/person
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;


    /*  FOOTER BİLGİLERİ */

    @Column(name = "footer_organization_title", length = 255)
    private String footerOrganizationTitle;

    @Column(name = "footer_address", length = 255)
    private String footerAddress;

    @Column(name = "footer_city_country", length = 255)
    private String footerCityCountry;

    @Column(name = "footer_year_text", length = 100)
    private String footerYearText;

    @Column(name = "footer_phone", length = 100)
    private String footerPhone;

    @Column(name = "footer_email", length = 255)
    private String footerEmail;

    /*  SOSYAL MEDYA LİNKLERİ */

    @Column(name = "footer_facebook_url", length = 500)
    private String footerFacebookUrl;

    @Column(name = "footer_twitter_url", length = 500)
    private String footerTwitterUrl;

    @Column(name = "footer_instagram_url", length = 500)
    private String footerInstagramUrl;

    @Column(name = "footer_linkedin_url", length = 500)
    private String footerLinkedinUrl;
}
