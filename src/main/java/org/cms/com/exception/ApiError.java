package org.cms.com.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<T> {// Generic yapıyoruz ki farklı tiplerde hatalar dönebilelim. Dışardan T tipinde bir data gelecek.

    // Ne hata dönülürse dönülsün bu 3 değişkeni içersin istiyoruz. Response'u standartlaştırıyoruz.
    private String id;
    private Date errorTime;
    private T errors; // Ne tipte hata dönecekse o tip burada olacak.
    // private Map<String, List<String>> errors;Bu şekilde verdiğimizde burayı statikleştiriyoruz.
    // Daha farklı bir tipte bir değer girilmesini engellemiş oluyoruz.
}