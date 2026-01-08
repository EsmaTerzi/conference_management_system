package org.cms.com.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorDto {
    private Long id;
    private String name;
    private String type; // Financial, Educational, General
    private String logoUrl;
    private Long conferenceId;
}
