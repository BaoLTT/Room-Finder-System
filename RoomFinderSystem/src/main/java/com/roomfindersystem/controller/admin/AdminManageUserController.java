package com.roomfindersystem.controller.admin;

import com.roomfindersystem.dto.UserDto;
import com.roomfindersystem.entity.AddressEntity;
import com.roomfindersystem.entity.UserEntity;
import com.roomfindersystem.service.AddressService;
import com.roomfindersystem.service.AdminManageUserService;
import com.roomfindersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class AdminManageUserController {
    private final AdminManageUserService adminManageUserService;
    private final UserService userService;
    @Autowired
    private AddressService addressService;

    public AdminManageUserController(AdminManageUserService adminEditUserService, UserService userService) {
        this.adminManageUserService = adminEditUserService;
        this.userService = userService;
    }

    @GetMapping()
    public String getListUserForm(Model model) {
        List<UserDto> users = adminManageUserService.getAll();
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
        adminManageUserService.insertUser(userDto, file);
        return "redirect:/admin/user";
    }


    @GetMapping("/updateUser/{id}")
    public String getFormUpdate(@PathVariable(name = "id")Integer id, Model model) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);
        return "admin/edit-user";
    }

    @PostMapping("/update")
    public String updateUserByAdmin(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        if(userDto.getAddressID()==0){
            AddressEntity address = new AddressEntity("a",userDto.getAddressDetails().trim(),userDto.getProvinceId(),userDto.getDistrictId(),userDto.getWardId());
            userDto.setAddressID(addressService.insertAddress(address));
        }else{
            if(userDto.getProvinceId()==0){
                Optional<AddressEntity> newAddress = addressService.findbyId(userDto.getAddressID());
                AddressEntity address = new AddressEntity("a",userDto.getAddressDetails(),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
                addressService.updateAddress(address,userDto.getAddressID());
            }else{
                AddressEntity address = new AddressEntity("a",userDto.getAddressDetails(),userDto.getProvinceId(),userDto.getDistrictId(),userDto.getWardId());
                addressService.updateAddress(address,userDto.getAddressID());
            }
        }
        userService.updateProfile(userDto, file);
        return "redirect:/admin/user/updateUser/" + userDto.getUserId();
    }
}
