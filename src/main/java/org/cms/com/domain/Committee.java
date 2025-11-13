package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "committee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Committee {
//kevser
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "committee_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @Column(name = "committee_type", nullable = false, length = 50)
    private String committeeType;   // 'BILIM', 'DUZENLEME', ...

    @Column(name = "member_name", nullable = false, length = 150)
    private String memberName;

    @Column(name = "affiliation", length = 150)
    private String affiliation;     // hangi kuruma bağlı
}

