package org.cms.com.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "Lütfen isminizi girin.")
    private String name;

    @Column(name = "person_surname", nullable = false, length = 100)
    @NotEmpty(message = "Lütfen soyisminizi girin.")
    private String surname;

    @Column(name = "password_hash", nullable = false, length = 255)
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır.")
    private String passwordHash;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    @NotEmpty(message = "Lütfen emailinizi girin.")
    private String email;

    @Column(name = "title", length = 100)
    @NotEmpty(message = "Lütfen ünnvanınızı girin.")
    private String title;
}
