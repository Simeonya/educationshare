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
public class IndexMapping {

    /**
     * Front page
     * @return Front page files
     */
    @GetMapping("/")
    public String frontPage() {

        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/index/index.html")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping("/style")
    public String frontPageStyle() {

        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/index/style.css")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping("/script")
    public String frontPageScript() {
        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/index/script.js")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping(value = "/favicon")
    public ResponseEntity<Resource> viewImg() throws IOException {
        String inputFile = "./src/main/resources/web/index/favicon.png";
        Path path = new File(inputFile).toPath();
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }
}
