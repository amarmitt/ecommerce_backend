package com.vegitable.store.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException customException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(customException.getMessage(),
                //      customException,
                status,
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));

        return new ResponseEntity<>(apiException, status);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleCustomException(ConstraintViolationException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage() + " , " + e.getConstraintName(),
                //      customException,
                status,
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));

        return new ResponseEntity<>(apiException, status);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, ApiException>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, ApiException> map = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            ApiException apiException = new ApiException(error.getDefaultMessage(),
                    status,
                    ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));
            map.put(fieldName, apiException);
        });
        return new ResponseEntity<>(map, status);
    }

    @Data
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    static class ApiException {
        String message;
        // Throwable throwable;
        HttpStatus httpStatus;
        ZonedDateTime timestamp;

    }
}
