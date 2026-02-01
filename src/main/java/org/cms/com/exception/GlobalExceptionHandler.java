package org.cms.com.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice// Exception handler sınıfı olduğunu belirtir.
public class GlobalExceptionHandler {

    //Spring validation'dan fırlatılan hataları alıp yönetmek ve adam akıllı response dönmek.

    private List<String> addMapValue(List<String> list, String newValue){//Gelen listeye yeni bir değer ekleyen yardımcı metot.
        list.add(newValue);
        return list;
    }

    //value değeri fırlatılan hatanın class'ını belirtir.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)//backendde fırlatılan MethodArgumentNotValidException hatalarını yakalar.
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {//Fırlatılan hatayı parametre olarak alıyoruz içindeki bilgilere erişmek için.
        //Exceptionları dto'daki fieldlara göre alıp yönetmek istiyoruz. Kategorilendirmek için de map kullanıyoruz.
        Map<String, List<String>> errorsMap = new HashMap<>();//Hata mesajlarını tutmak için map oluşturduk. Bir field'ın birçok hata mesajı olabileceği için list kullandık.

        for( ObjectError objError : ex.getBindingResult().getAllErrors()){//Hatanın içindeki tüm hataları alıyoruz. Yani tüm field hatalarını alıyoruz.
            String fieldName =  ((FieldError)objError).getField();//Hatanın hangi field(değişken)'dan kaynaklandığını alıyoruz. FieldError'a cast ediyoruz.
            if(errorsMap.containsKey(fieldName)){
                //Daha önce bu field için hata mesajı eklenmişse, mevcut listeye yeni hata mesajını ekliyoruz.
                errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName),objError.getDefaultMessage()));
            }else{
                errorsMap.put(fieldName, addMapValue(new ArrayList<>(),objError.getDefaultMessage()));//Hata mesajını map'e ekliyoruz.

            }
        }
        return ResponseEntity.badRequest().body(createApiError(errorsMap));//400 Bad Request döndürüyoruz.
    }

    private <T> ApiError<T> createApiError(T errors){
        ApiError<T> apiError = new ApiError<T>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new Date());
        apiError.setErrors(errors);
        return apiError;
    }
}
