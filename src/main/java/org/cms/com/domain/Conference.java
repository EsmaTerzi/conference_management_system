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

    @Column(name = "conference_name", nullable = false, length = 200)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
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
    private Person owner;   // NULL olabilir
}

