package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/sliderList/insert")
    public String viewSliderDetail(Model model){
        return "/admin/slider_details";
    }
}
