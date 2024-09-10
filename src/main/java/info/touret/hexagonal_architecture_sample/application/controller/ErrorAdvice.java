package info.touret.hexagonal_architecture_sample.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@ControllerAdvice("info.touret.hexagonal_architecture_sample.application.controller")
public class ErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnknownException(Exception ex) {
        ProblemDetail problemDetails = ProblemDetail
                .forStatusAndDetail
                        (HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        problemDetails.setType(URI.create(
                "http://localhost:8080/errors/internal-server-error"));
        problemDetails.setTitle("INTERNAL_SERVER_ERROR");
        return problemDetails;
    }
}
