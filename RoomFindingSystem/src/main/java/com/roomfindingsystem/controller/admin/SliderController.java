package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SliderController {
    @Autowired
    private SliderService sliderService;
    @GetMapping("/sliderList")
    public String viewSlider(Model model){
        model.addAttribute("sliderList", sliderService.viewAll() );

        return "/admin/list_slider";
    }
}
