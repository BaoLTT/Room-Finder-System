package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            UserDto userDto = userService.findUserDtoByEmail(email);
            model.addAttribute("user", userDto);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("user", user);
        return "admin/adminProfile";
    }

    @PostMapping("/profile/update")
    public String updateUser(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        userService.updateProfile(userDto, file);
        return "redirect:/admin/profile";
    }
}
