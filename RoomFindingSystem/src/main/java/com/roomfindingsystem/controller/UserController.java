package com.roomfindingsystem.controller;


import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.EmailSenderService;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.service.impl.Smsservice;

import com.roomfindingsystem.dto.Smsrequest;
import com.roomfindingsystem.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
     private Smsservice smsservice;


    public UserController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public UserController() {
    }

    @RequestMapping(value = "register")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    //register

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, Model model, Smsrequest smsrequest, HttpSession session) {
        try {
            if (result.hasErrors()) {
                return "register";
            }
            if (userService.findByEmail(user.getEmail()).isPresent()) {
                model.addAttribute("mess", "Email đã tồn tại. Hãy nhập Email mới!");
                return "register";
            }
            session.setAttribute("otp-register", otpCode());
            session.setMaxInactiveInterval(360);
            String subject = "Hello Here Is Your Code OTP!";
            String mess = "Hi You@" + " \nDear " + user.getFirstName() + " " + user.getLastName() + " " + "Here is your OTP Code: " + session.getAttribute("otp-register") + " Plaese input to form!" + "\n Thanks!";
            this.emailSenderService.sendEmail(user.getEmail(), subject, mess);
// comment phần sms lúc nào cần send thì mở ra
//        String phone = "+84".concat(user.getPhone().substring(1,10));
//        System.out.println(phone);
//        smsrequest.setNumber(phone);
//        smsrequest.setMessage(mess);
//        smsservice.sendsms(smsrequest);

            session.setAttribute("userid", user.getUserId());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("firstname", user.getFirstName());
            session.setAttribute("lastname", user.getLastName());
//        session.setAttribute("dob", user.getDob());
            session.setAttribute("phone", user.getPhone());
//        session.setAttribute("gender", user.getGender());
            session.setAttribute("password", user.getPassword());
            return "redirect:/otp-check";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }



    }

    @RequestMapping(value = "re-send")
    public String resend(HttpSession session) {
        try {
            //
            session.removeAttribute("otp-register");
            session.setAttribute("otp-register", otpCode());
            session.setMaxInactiveInterval(360);
            String subject = "Hello Here Is Your Code OTP!";
            String mess = "Hi You@" + " \nDear " + session.getAttribute("firstname") + " " + session.getAttribute("lastnamename") + " " + "Here is your OTP Code: " + session.getAttribute("otp-register") + " Plaese input to form!" + "\n Thanks!";
            this.emailSenderService.sendEmail((String) session.getAttribute("email"), subject, mess);
            return "redirect:/otp-check";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }

    }

    public String otpCode() {
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        return String.valueOf(code);
    }

    //forgot pass
    @RequestMapping(value = "recover")
    public String forgotPass() {
        return "recoverPage";
    }

    @RequestMapping(value = "send-otp-recover")
    public String sendMailForgotPass(@RequestParam("emailaddress") String email, HttpSession session) {
        try {
            String subject = "Hello Here Is Your Code OTP!";
            String mess = "Hi You@" + " " + "Here is your OTP Code: " + otpCode() + " Plaese input to form!" + "\n Thanks!";
            this.emailSenderService.sendEmail(email, subject, mess);
            session.setAttribute("recoverOtp", otpCode());
            session.setMaxInactiveInterval(360);
            return "recoverPage";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }

    }

    // change password
    @RequestMapping(value = "change-password")
    public String changePass() {
        return "change-password-form";
    }

    @RequestMapping(value = "save-change-password")
    public String saveChangePass(@RequestParam("old_password") String oldPassword, @RequestParam("confirm_password") String newPassword, Model model) {

        try {
            //        passwordEncoder.encode(oldPassword).equals(userService.getUserForChangePass("binhnhhe153478@fpt.edu.vn").toString());
            System.out.println(passwordEncoder.matches(oldPassword, userService.getUserForChangePass("binhnhhe153478@fpt.edu.vn").toString()));
            System.out.println(passwordEncoder.encode(oldPassword));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            System.out.println(email);
            if (passwordEncoder.matches(oldPassword, userService.getUserForChangePass(email))) {
                userService.recoverPassword(passwordEncoder.encode(newPassword),email);
                model.addAttribute("mess", "Mật Khẩu đã được đổi thành công");
                return "change-password-form";
            }
            model.addAttribute("mess1", "Mật Khẩu cũ Không Trùng");
            System.out.println("Remove faild");
            return "change-password-form";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }

    }
    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            UserDto userDto = userService.findUserDtoByEmail(email);
            model.addAttribute("user", userDto);
            model.addAttribute("request",request);
            return "profile";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute(name = "user") UserDto userDto, @RequestParam("file") MultipartFile file) throws IOException {
        userService.updateProfile(userDto, file);
        return "redirect:/profile";
    }
}
