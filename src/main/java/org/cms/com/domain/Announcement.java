package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long id;

    @Column(name = "announcement_title", nullable = false, length = 200)
    private String title;

    @Column(name = "announcement_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "announcement_desc", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;
}
// Kevser: GitHub contribution denemesi

