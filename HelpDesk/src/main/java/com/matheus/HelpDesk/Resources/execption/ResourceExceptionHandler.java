package com.matheus.HelpDesk.Resources.execption;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjNotFoundException.class)
    public ResponseEntity<StandardError> objNotFound(Exception ex,
                                                     HttpServletRequest request) {

        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Object Not Found",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(Exception ex,
                                                                         HttpServletRequest request) {

        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Violação de dados",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
                                                                         HttpServletRequest request) {

    ValidationError errors = new ValidationError(LocalDateTime.now(),
                                                HttpStatus.BAD_REQUEST.value(),
                                                "Validation Error",
                                                "Erro na validação dos campos",
                                                request.getRequestURI());

    for( FieldError x : ex.getBindingResult().getFieldErrors()){
        errors.addError(x.getField(), x.getDefaultMessage());
    }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }
}

