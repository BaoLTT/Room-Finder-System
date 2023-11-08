package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminProfileController {
    @Autowired
    private UserService userService;
    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        UserDto userDto = userService.findById(1);
        model.addAttribute("user", userDto);
        return "admin/adminProfile";
    }

    @PostMapping("/profile/update")
    public String updateUser(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        userDto.setUserId(1);
        userService.updateProfile(userDto, file);
        return "redirect:/admin/profile";
    }
}
