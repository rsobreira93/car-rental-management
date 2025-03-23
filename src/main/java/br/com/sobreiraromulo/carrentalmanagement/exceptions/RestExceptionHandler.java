package br.com.sobreiraromulo.carrentalmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.security.auth.message.AuthException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CsvException.class)
    public ProblemDetail handleCsvException(CsvException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(CarAlreadyExists.class)
    public ProblemDetail handleCarAlreadyExistsException(CarAlreadyExists e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ProblemDetail> handleAuthException(AuthException e) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        pd.setTitle("Authentication Error");
        pd.setDetail(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(pd);
    }

    @ExceptionHandler(UserNotFound.class)
    public ProblemDetail handleUserNotFoundException(UserNotFound e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ProblemDetail handleUserAlreadyExistsException(UserAlreadyExists e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var fieldErros = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Your request parameters didn't validate.");
        pb.setProperty("invalid-params", fieldErros);

        return pb;
    }

    private record InvalidParam(String name, String reason) {
    }
}