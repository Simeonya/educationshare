package de.educationshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }



    @GetMapping("/")
    public String frontPage() {
        return "Main Page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Admin Page";
    }

    @GetMapping("/create")
    public String createRoomPage() {
        return "Create Room Page";
    }

    @GetMapping("/room/{id}/")
    public String RoomPage(@PathVariable String id) {
        return "Room Page " + id;
    }
    @GetMapping("/room")
    public RedirectView RoomPageNoId() {
       return new RedirectView("/");
    }






}