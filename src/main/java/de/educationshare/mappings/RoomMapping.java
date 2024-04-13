package de.educationshare.mappings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomMapping {

    /**
     * Room page
     * @param id Which room to sho w
     * @return Room Page Files
     */
    @GetMapping("/room/{id}/")
    public String RoomPage(@PathVariable String id) {
        return "Room Page " + id;
    }
}
