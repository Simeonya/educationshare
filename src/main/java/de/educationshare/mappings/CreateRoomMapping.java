package de.educationshare.mappings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateRoomMapping {

    /**
     * Create Room Page
     * @return Create Room Page Files
     */
    @GetMapping("/create")
    public String createRoomPage() {
        return "Create Room Page";
    }
}
