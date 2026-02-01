package org.cms.com.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantRegisterRequest {

        private String name;
        private String surname;
        private String organisation;
        private String email;
        private String title;
        private String typeOfParticipation;
        private String password;
        private boolean isPayment;
        private String documentUrl;
        private String documentStatus;
       // private Set<ConferenceDto> conferences;
        private Long conferenceId;


}
