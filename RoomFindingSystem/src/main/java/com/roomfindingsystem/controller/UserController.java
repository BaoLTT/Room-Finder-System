package com.roomfindingsystem.controller;


import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.EmailSenderService;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.vo.UserDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    public UserController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }


    @RequestMapping(value = "register")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }




    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, Model model, @RequestParam("repassword") String repassword, HttpSession session) {
        // check confirm password
//        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "register";
        }
        session.setAttribute("otp-register",otpCode());
        session.setMaxInactiveInterval(360);
        String mess = "Hi You@"+" \nDear "+user.getFirstName()+" "+ user.getLastName()+" "+"Here is your OTP Code: "+session.getAttribute("otp-register")+" Plaese input to form!"+"\n Thanks!";
        this.emailSenderService.sendEmail(user.getEmail(), "emailMessage.getSubject()", mess);

//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(user.getUserId());
//        userEntity.setEmail(user.getEmail());
//        userEntity.setFirstName(user.getFirstName());
//        userEntity.setLastName(user.getLastName());
//        userEntity.setDob(user.getDob());
//        userEntity.setPhone(user.getPhone());
//        userEntity.setGender(user.getGender());
//        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
//        userEntity.setCreatedDate(null);
//        userEntity.setAddressId(1);
//        userEntity.setFacebookId(null);
//        userEntity.setGmailId(null);
//        userEntity.setImageLink(null);
//        userEntity.setLastModifiedDate(null);
//        userEntity.setRoleId(1);
//        userService.saveUser(user);
        return "redirect:/otp-check";
    }

    public String otpCode(){
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        return String.valueOf(code);
    }
}

