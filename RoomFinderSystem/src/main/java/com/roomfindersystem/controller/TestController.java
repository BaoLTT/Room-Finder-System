package com.roomfindersystem.controller;

import com.roomfindersystem.entity.UserEntity;
import com.roomfindersystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getHome(Model model, Authentication authentication, HttpServletRequest request){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(currentUserName).get();
        model.addAttribute("user", user);


        // Set vào session

        return "home";
    }



    @GetMapping("/test")
    public String getHello(){
        return "hello";
    }
}
