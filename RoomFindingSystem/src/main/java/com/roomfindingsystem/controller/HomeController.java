package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;
import com.roomfindingsystem.service.impl.GcsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private HouseService houseService;

    private RoomService roomService;

    private FeedbackService feedbackService;

    private SliderService sliderService;

    private HouseTypeService houseTypeService;

    private ServiceDetailService serviceDetailService;

    private UserService userService;



    public HomeController(HouseService houseService, RoomService roomService,
                          FeedbackService feedbackService, SliderService sliderService,
                          HouseTypeService houseTypeService, ServiceDetailService serviceDetailService,
                          UserService userService){
        super();
        this.houseService = houseService;
        this.roomService = roomService;
        this.feedbackService  = feedbackService;
        this.sliderService = sliderService;
        this.houseTypeService = houseTypeService;
        this.serviceDetailService = serviceDetailService;
        this.userService = userService;

    }

    @GetMapping("")
    public String viewHomepage(
            final Model model, HttpServletRequest request){



        model.addAttribute("houses", houseService.viewHouseInHome() );
        model.addAttribute("housetypes", houseTypeService.findAll() );
        model.addAttribute("houseservices", serviceDetailService.getAllService());
        model.addAttribute("rooms", roomService.viewRoomInHome());
        model.addAttribute("feedbacks", feedbackService.viewTop4Home());
        model.addAttribute("sliders", sliderService.viewTop7Home());
        model.addAttribute("request",request);
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = null;
        if (userService.findByEmail(currentUserName).isPresent()) {
             user = userService.findByEmail(currentUserName).get();
        }


        // Lưu user vào session
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);



        return "homepage";
    }
}