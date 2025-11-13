package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @Column(name = "day_tabs", length = 100)
    private String dayTabs;        // "Day 1" vb.

    @Column(name = "time_intervals", length = 100)
    private String timeIntervals;  // "09:00-10:00"

    @Column(name = "session_title", nullable = false, length = 255)
    private String sessionTitle;

    @Column(name = "subtopics", columnDefinition = "TEXT")
    private String subtopics;
}

