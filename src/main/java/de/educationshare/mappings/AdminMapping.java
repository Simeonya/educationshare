package de.educationshare.mappings;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class AdminMapping {
    /**
     * Admin page
     * @return Admin page files
     */
    @GetMapping("/admin")
    public String adminPage() {

        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/admin/index.html")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping("/admin/style")
    public String adminPageStyle() {

        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/admin/style.css")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping("/admin/script")
    public String adminPageScript() {
        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/admin/script.js")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

}
