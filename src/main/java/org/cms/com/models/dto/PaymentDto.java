package org.cms.com.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;

    @Size(max = 255)
    private String bankName;

    @Size(max = 255)
    private String bankNameOptional;

    @Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z0-9]{11,30}$", message = "IBAN format gecersiz")
    private String iban;

    @Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z0-9]{11,30}$", message = "IBAN format gecersiz")
    private String ibanOptional;

    @Email(message = "Email format gecersiz")
    @Size(max = 255)
    private String documentEmail;

    @Size(max = 5000)
    private String textArea;

    private String paymentFeeText;
}
