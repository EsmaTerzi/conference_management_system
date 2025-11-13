package org.cms.com.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pictures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;

    @Column(name = "caption", length = 255)
    private String caption;
}

