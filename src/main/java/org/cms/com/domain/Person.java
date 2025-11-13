package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "person_name", nullable = false, length = 100)
    private String name;

    @Column(name = "person_surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "title", length = 100)
    private String title;
}

