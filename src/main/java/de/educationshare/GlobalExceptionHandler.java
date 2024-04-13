package de.educationshare;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> createResponseEntity(Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        //if(request.getHeader("Accept") == null) return new ResponseEntity(body, headers, statusCode);
        //if(request.getHeader("Accept").toLowerCase().contains("text/html")) return new ResponseEntity<>("Suck my dick. <a href='https://onlyfans.com/jojocrafttv'>Click here to suck.</a>", statusCode);
        return new ResponseEntity(body, headers, statusCode);
    }
}
