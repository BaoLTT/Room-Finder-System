package com.roomfindingsystem.controller;

import com.roomfindingsystem.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class  MainController {
    private HouseService houseService;

    private RoomService roomService;

    private FeedbackService feedbackService;

    private SliderService sliderService;

    private HouseTypeService houseTypeService;

    private ServiceHouseService serviceHouseService;

    public MainController(HouseService houseService,  RoomService roomService,
                          FeedbackService feedbackService, SliderService sliderService,
                          HouseTypeService houseTypeService, ServiceHouseService serviceHouseService){
        super();
        this.houseService = houseService;
        this.roomService = roomService;
        this.feedbackService  = feedbackService;
        this.sliderService = sliderService;
        this.houseTypeService = houseTypeService;
        this.serviceHouseService = serviceHouseService;
    }

    @GetMapping("")
    public String viewHomepage(
            final Model model){

        model.addAttribute("houses", houseService.viewHouseInHome() );
        model.addAttribute("housetypes", houseTypeService.findAll() );
        model.addAttribute("houseservices", serviceHouseService.findAll());
        model.addAttribute("rooms", roomService.viewRoomInHome());
        model.addAttribute("feedbacks", feedbackService.viewTop4Home());
        model.addAttribute("sliders", sliderService.viewTop4Home());
        return "Homepage";
    }
}