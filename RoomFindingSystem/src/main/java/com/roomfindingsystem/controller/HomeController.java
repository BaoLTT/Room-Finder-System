package com.roomfindingsystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome(Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        model.addAttribute("currentUserName", currentUserName);
        model.addAttribute("status", SecurityContextHolder.getContext().getAuthentication().getCredentials()=="");


        return "home";
    }



    @GetMapping("/test")
    public String getHello(){
        return "hello";
    }
}
