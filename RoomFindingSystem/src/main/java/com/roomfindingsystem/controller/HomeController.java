package com.roomfindingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/test")
    public String getHello(){
        return "hello";
    }
}
