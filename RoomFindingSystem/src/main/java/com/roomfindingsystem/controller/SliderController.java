package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.SliderEntity;
import com.roomfindingsystem.service.SliderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@RequestMapping("/slider")
@Controller
public class SliderController {
    @Autowired
    SliderService sliderService;
    @GetMapping("/{id}")
    public String view(Model model, @PathVariable("id") int id, HttpServletRequest request){
        SliderEntity slider = sliderService.getSliderById(id);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = slider.getCreatedDate().format(formatter);
//        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("slider", slider);
        model.addAttribute("request",request);
        return "slider-detail";
    }
}
