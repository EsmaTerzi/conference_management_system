package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "organisation")
    private String organisation;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "type_of_participation")
    private String typeOfParticipation;

    @Column(name = "password")
    private String password;

    @Column(name = "is_payment")
    private boolean isPayment;

    @Column(name = "document_url")
    private String documentUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_status")
    private DocumentStatus documentStatus = DocumentStatus.PENDING;

    @ManyToMany
    @JoinTable(
            name = "participant_conference",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "conference_id")
    )
    private Set<Conference> conferences = new HashSet<>();

}
