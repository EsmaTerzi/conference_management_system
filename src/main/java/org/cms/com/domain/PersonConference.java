package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person_conference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonConference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   // SQL'de id diye tanımlamıştınız
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "committee", length = 100)
    private String committee;   // "Bilim Kurulu" gibi
}

