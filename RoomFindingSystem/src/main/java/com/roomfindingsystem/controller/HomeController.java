package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;
import com.roomfindingsystem.service.impl.GcsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private HouseTypeService houseTypeService;
    @Autowired
    private ServiceDetailService serviceDetailService;
    @Autowired
    private UserService userService;



    @GetMapping("")
    public String viewHomepage(
            final Model model, HttpServletRequest request){



        model.addAttribute("houses", houseService.viewHouseInHome() );
        model.addAttribute("housetypes", houseTypeService.findAll() );
        model.addAttribute("houseservices", serviceDetailService.getAllService());
        model.addAttribute("rooms", roomService.viewRoomInHome());
        model.addAttribute("feedbacks", feedbackService.viewTop4Home());
        model.addAttribute("news", newsService.viewTop7Home());
        model.addAttribute("request",request);
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = null;
        if (userService.findByEmail(currentUserName).isPresent()) {
             user = userService.findByEmail(currentUserName).get();
        }

        model.addAttribute("user",user);

//         Lưu user vào session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);



        return "homepage";
    }


}