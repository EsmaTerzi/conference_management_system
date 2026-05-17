package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "bank_name", length = 255)
    private String bankName;

    @Column(name = "bank_name_optional", length = 255)
    private String bankNameOptional;

    @Column(name = "iban", length = 255)
    private String iban;

    @Column(name = "iban_optional", length = 255)
    private String ibanOptional;

    @Column(name = "document_email", length = 255)
    private String documentEmail;

    @Column(name = "text_area", columnDefinition = "TEXT")
    private String textArea;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
}
