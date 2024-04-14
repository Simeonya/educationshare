package de.educationshare.mappings;

import de.educationshare.Main;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@ControllerAdvice
@RestController
public class ErrorMapping extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> createResponseEntity(Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (request.getHeader("Accept") == null) return new ResponseEntity<>(body, headers, statusCode);
        if (Objects.requireNonNull(request.getHeader("Accept")).toLowerCase().contains("text/html")) {
            try {
                String errorPageContent = new String(Files.readAllBytes(Paths.get("./src/main/resources/web/error/index.html")));
                errorPageContent = errorPageContent.replaceAll("%errorcode%", String.valueOf(statusCode.value())).replaceAll("%errorname%", Character.toUpperCase(HttpStatus.valueOf(statusCode.value()).name().toLowerCase().charAt(0)) + HttpStatus.valueOf(statusCode.value()).name().toLowerCase().substring(1).replace("_", " "));
                return new ResponseEntity<>(errorPageContent, headers, statusCode);
            } catch (IOException e) {
                Main.getInstance().getLogger().error("Error reading file", e);
                return new ResponseEntity<>("Error reading file", headers, statusCode);
            }
        }
        return new ResponseEntity<>(body, headers, statusCode);
    }

    @GetMapping("/error/style")
    public String adminPageStyle() {

        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/error/style.css")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping("/error/script")
    public String adminPageScript() {
        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/error/script.js")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }
}
