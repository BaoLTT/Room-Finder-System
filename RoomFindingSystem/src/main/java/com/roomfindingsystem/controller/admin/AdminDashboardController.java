package com.roomfindingsystem.controller.admin;


import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.service.PostService;
import com.roomfindingsystem.service.ReportService;
import com.roomfindingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {
    @Autowired
    HouseService houseService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    ReportService reportService;
    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        model.addAttribute("numberOfHouses", houseService.countHousesInAdmin());
        model.addAttribute("numberOfUsers", userService.countUserInAdmin());
        model.addAttribute("numberOfPosts", postService.countPosts());
        model.addAttribute("numberOfReports", reportService.countReports());
        return "admin/adminDashboard";
    }
}
