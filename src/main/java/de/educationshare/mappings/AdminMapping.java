package de.educationshare.mappings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminMapping {
    /**
     * Admin page
     * @return Admin Page Files
     */
    @GetMapping("/admin")
    public String adminPage() {
        return "Admin Page";
    }
}
