package org.cms.com.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Spring validation'dan fırlatılan hataları alıp yönetmek ve adam akıllı response dönmek.

    private List<String> addMapValue(List<String> list, String newValue){//Gelen listeye yeni bir değer ekleyen yardımcı metot.
        list.add(newValue);
        return list;
    }

    //value değeri fırlatılan hatanın class'ını belirtir.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)//backendde fırlatılan MethodArgumentNotValidException hatalarını yakalar.
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {//Fırlatılan hatayı parametre olarak alıyoruz içindeki bilgilere erişmek için.
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

    // JPA validation hatalarını yakalamak için (Entity'lerde @NotEmpty, @Size gibi validationlar)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, List<String>> errorsMap = new HashMap<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            // propertyPath'den sadece field adını alıyoruz (örn: "name" için "name")
            String fieldName = violation.getPropertyPath().toString();
            // Eğer path'de nokta varsa, son kısmı alıyoruz (örn: "person.name" -> "name")
            if (fieldName.contains(".")) {
                fieldName = fieldName.substring(fieldName.lastIndexOf('.') + 1);
            }

            String errorMessage = violation.getMessage();

            if(errorsMap.containsKey(fieldName)){
                errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), errorMessage));
            } else {
                errorsMap.put(fieldName, addMapValue(new ArrayList<>(), errorMessage));
            }
        }

        return ResponseEntity.badRequest().body(createApiError(errorsMap));
    }

    private <T> ApiError<T> createApiError(T errors){
        ApiError<T> apiError = new ApiError<>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new Date());
        apiError.setErrors(errors);
        return apiError;
    }

    // 404 Not Found hatalarını yakalamak için
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleNotFoundException(NoHandlerFoundException ex) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        errorsMap.put("path", List.of("Endpoint bulunamadı: " + ex.getRequestURL()));
        errorsMap.put("method", List.of("HTTP Method: " + ex.getHttpMethod()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createApiError(errorsMap));
    }

    // Genel Exception yakalayıcı (diğer tüm hatalar için)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleGeneralException(Exception ex) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        errorsMap.put("error", List.of(ex.getMessage() != null ? ex.getMessage() : "Beklenmeyen bir hata oluştu"));
        errorsMap.put("type", List.of(ex.getClass().getSimpleName()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createApiError(errorsMap));
    }
}
