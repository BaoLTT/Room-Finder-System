package com.roomfindingsystem.controller;



import com.roomfindingsystem.config.Role;

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
    public String save(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, Model model,  HttpSession session) {

        // check confirm password
//        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "register";
        }

        if(userService.findByEmail(user.getEmail()).isPresent()){
            model.addAttribute("mess","Email đã tồn tại. Hãy nhập Email mới!");
            return "register";
        }
        session.setAttribute("otp-register", otpCode());
        session.setMaxInactiveInterval(360);
        String subject = "Hello Here Is Your Code OTP!";
        String mess = "Hi You@" + " \nDear " + user.getFirstName() + " " + user.getLastName() + " " + "Here is your OTP Code: " + session.getAttribute("otp-register") + " Plaese input to form!" + "\n Thanks!";
        this.emailSenderService.sendEmail(user.getEmail(), subject, mess);

        session.setAttribute("userid",user.getUserId());
        session.setAttribute("email",user.getEmail());
        session.setAttribute("firstname",user.getFirstName());
        session.setAttribute("lastname",user.getLastName());
        session.setAttribute("dob",user.getDob());
        session.setAttribute("phone",user.getPhone());
        session.setAttribute("gender",user.getGender());
        session.setAttribute("password",user.getPassword());
        return "redirect:/otp-check";

    }
    @RequestMapping(value = "re-send")
    public String resend(HttpSession session) {
        //
        session.removeAttribute("otp-register");
        session.setAttribute("otp-register", otpCode());
        session.setMaxInactiveInterval(360);
        String subject = "Hello Here Is Your Code OTP!";
        String mess = "Hi You@" + " \nDear " + session.getAttribute("firstname") + " " + session.getAttribute("lastnamename") + " " + "Here is your OTP Code: " + session.getAttribute("otp-register") + " Plaese input to form!" + "\n Thanks!";
        this.emailSenderService.sendEmail((String) session.getAttribute("email"), subject, mess);
        return "redirect:/otp-check";
    }

    public String otpCode() {

        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        return String.valueOf(code);
    }
}

