package com.roomfindingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/slider")
@Controller
public class SliderController {
    @GetMapping
    public String view(){
        return "slider-detail";
    }
}
