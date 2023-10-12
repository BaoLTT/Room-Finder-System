package com.roomfindingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HouseListController {
    @GetMapping("/houselist")
    public String getAll(){
        return "listing";
    }
}
