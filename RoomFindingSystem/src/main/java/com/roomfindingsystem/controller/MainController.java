package com.roomfindingsystem.controller;

<<<<<<< Updated upstream
public class MainController {
}
=======
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class  MainController {
    @GetMapping("/")
    public String getIndex(){
        return "listing";
    }

}
>>>>>>> Stashed changes
