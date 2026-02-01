package org.cms.com.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotEmpty(message = "Lütfen isminizi girin.")
    @Size(min = 2, max = 100, message = "İsim en az 2, en fazla 100 karakter olmalıdır.")
    private String personName;

    @NotEmpty(message = "Lütfen soyisminizi girin.")
    @Size(min = 2, max = 100, message = "Soyisim en az 2, en fazla 100 karakter olmalıdır.")
    private String personSurname;

    @NotEmpty(message = "Lütfen emailinizi girin.")
    @Email(message = "Geçerli bir email adresi giriniz.")
    private String email;

    @NotEmpty(message = "Lütfen şifrenizi girin.")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır.")
    private String password;

    private String title;    // opsiyonel
}
