package br.com.medvoll.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice
public class HandlerException {

    private record ErrorDataResponse(String campo, String message){

        public ErrorDataResponse(FieldError errors){
            this(errors.getField(), errors.getDefaultMessage());
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exception() {
        return notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception(MethodArgumentNotValidException exception) {

        final var errors = exception.getFieldErrors();


        return ResponseEntity.badRequest().body(errors.stream().map(ErrorDataResponse::new).toList());
    }
}