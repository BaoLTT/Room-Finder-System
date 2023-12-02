package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.AdminManageUserService;
import com.roomfindingsystem.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminManageUserController {
    private final AdminManageUserService adminManageUserService;
    private final UserService userService;

    public AdminManageUserController(AdminManageUserService adminEditUserService, UserService userService) {
        this.adminManageUserService = adminEditUserService;
        this.userService = userService;
    }

    @GetMapping()
    public String getListUserForm(Model model) {
        List<UserDto> users = adminManageUserService.getAll();
        model.addAttribute("users", users);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("user", user);
        return "admin/list-user";
    }

    @GetMapping("/insertUserPage")
    public String getInsertPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "admin/insert-user";
    }

    @PostMapping("/save")
    public String insertUserByAdmin(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        adminManageUserService.insertUser(userDto, file);
        return "redirect:/admin/user";
    }


    @GetMapping("/updateUser/{id}")
    public String getFormUpdate(@PathVariable(name = "id")Integer id, Model model) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);
        return "admin/edit-user";
    }

//    @GetMapping("/deleteUser/{id}")
//    public String delete(@PathVariable("id") Integer id, Model model){
//        adminManageUserService.deleteById(id);
//        return "redirect:/admin/user";
//    }

    @PostMapping("/update")
    public String updateUserByAdmin(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        userService.updateProfile(userDto, file);
        return "redirect:/admin/user/updateUser/" + userDto.getUserId();
    }
}
