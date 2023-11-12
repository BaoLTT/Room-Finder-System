package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.service.AdminEditUserService;
import com.roomfindingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminEditUserController {
    private final AdminEditUserService adminEditUserService;
    private final UserService userService;

    public AdminEditUserController(AdminEditUserService adminEditUserService, UserService userService) {
        this.adminEditUserService = adminEditUserService;
        this.userService = userService;
    }

    @GetMapping()
    public String getListUserForm(Model model) {
        List<UserDto> users = adminEditUserService.getAll();
        model.addAttribute("users", users);
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
        adminEditUserService.insertUser(userDto, file);
        return "redirect:/admin/user";
    }


    @GetMapping("/updateUser/{id}")
    public String getFormUpdate(@PathVariable(name = "id")Integer id, Model model) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);
        return "admin/edit-user";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        adminEditUserService.deleteById(id);
        return "redirect:/admin/user";
    }

    @PostMapping("/update")
    public String updateUserByAdmin(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        userService.updateProfile(userDto, file);
        return "redirect:/admin/user/updateUser/" + userDto.getUserId();
    }
}
