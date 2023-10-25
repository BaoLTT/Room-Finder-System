package com.roomfindingsystem.controller;

import com.roomfindingsystem.config.Contants;
import com.roomfindingsystem.config.Role;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.EmailSenderService;
import com.roomfindingsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller

public class OtpController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    Contants contants= new Contants();


    public OtpController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @RequestMapping(value = "otp-check", method = RequestMethod.GET)
    public String indexOtp() {
        return "otpConfirm";
    }

    @RequestMapping(value = "confirm-otp", method = RequestMethod.POST)
    public String checkOtp(HttpSession session, @RequestParam("otp") String otp, Model model) {
        String otpRegister = (String) session.getAttribute("otp-register");
        if (otp.equals(otpRegister)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId((Integer) session.getAttribute("userid"));
            userEntity.setEmail((String) session.getAttribute("email"));
            userEntity.setFirstName((String) session.getAttribute("firstname"));
            userEntity.setLastName((String) session.getAttribute("lastname"));
            userEntity.setDob((LocalDate) session.getAttribute("dob"));
            userEntity.setPhone((String) session.getAttribute("phone"));
            userEntity.setGender((Boolean) session.getAttribute("gender"));
            userEntity.setPassword(passwordEncoder.encode((String) session.getAttribute("password")));
            String role = (String) session.getAttribute("role");
            if (role != null) {
                userEntity.setRoleId(String.valueOf(Role.USER));
            } else {
                userEntity.setRoleId(String.valueOf(Role.LANDLORD));
            }
            userEntity.setCreatedDate(null);
            userEntity.setAddressId(1);
            userEntity.setFacebookId(null);
            userEntity.setGmailId(null);
            userEntity.setImageLink(null);
            userEntity.setLastModifiedDate(null);
            userService.saveUser(userEntity);
            return "redirect:/home";
        }
        model.addAttribute("mess","OTP is not correct! Please check your email.");
        return "otpConfirm";
    }
    @RequestMapping(value = "send-otp-recover", method = RequestMethod.POST)
    public String getMail( @RequestParam("emailaddress") String email, HttpSession session) {
        session.setAttribute("emailToReset",email);
        String otpCode=contants.otpCode();
        String subject = "Hello Here Is Your Code OTP!";
        String mess = "Hi You@" + " " + "Here is your OTP Code: " + otpCode + " Plaese input to form!" + "\n Thanks!";
        this.emailSenderService.sendEmail(email, subject, mess);
        session.setAttribute("recoverOtp",otpCode);
        session.setMaxInactiveInterval(360);
        return "confirmOtpRecover";
    }

    @RequestMapping(value = "confirm-otp-recover", method = RequestMethod.POST)
    public String recover( @RequestParam("otp") String otp, Model model,HttpSession session) {
        System.out.println(otp);
        System.out.println(session.getAttribute("recoverOtp"));
        if (session.getAttribute("recoverOtp").equals(otp)){
            //
            return "confirmNewPassword";
        }
        model.addAttribute("mess","OTP is not correct! Please check your email.");
        return "confirmOtpRecover";
    }

    @RequestMapping(value = "save-new-password", method = RequestMethod.POST)
    public String saveNewPassword(@RequestParam("password") String password, HttpSession session) {

        // from getMail
        String email = (String) session.getAttribute("emailToReset");
        if (userService.recoverPassword(passwordEncoder.encode(password),email)==1){
            return "redirect:/login";
        }

        return "confirmNewPassword";
    }


}