package de.educationshare.mappings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class HomeMapping {
    /**
     * Home page
     * @return Home page files
     */
    @GetMapping("/home")
    public String homePage() {

        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/home/index.html")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping("/home/style")
    public String homePageStyle() {

        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/home/style.css")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    @GetMapping("/home/script")
    public String homePageScript() {
        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/web/home/script.js")));
        } catch (IOException e) {
            return "Error reading file";
        }
    }

}
