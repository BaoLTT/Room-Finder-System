package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.AddressService;
import com.roomfindingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            UserDto userDto = userService.findUserDtoByEmail(email);
            model.addAttribute("user", userDto);
            return "admin/adminProfile";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/profile/update")
    public String updateUser(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        if(userDto.getProvinceId()==0){
            Optional<AddressEntity> newAddress = addressService.findbyId(userDto.getAddressID());
            AddressEntity address = new AddressEntity("a",userDto.getAddressDetails(),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
            addressService.updateAddress(address,userDto.getAddressID());
        }else{
            AddressEntity address = new AddressEntity("a",userDto.getAddressDetails(),userDto.getProvinceId(),userDto.getDistrictId(),userDto.getWardId());
            addressService.updateAddress(address,userDto.getAddressID());
        }
        userService.updateProfile(userDto, file);
        return "redirect:/admin/profile";
    }
}
