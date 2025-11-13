package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "important_dates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportantDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @Column(name = "imp_title", nullable = false, length = 200)
    private String title;

    @Column(name = "imp_date", nullable = false)
    private LocalDate date;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

