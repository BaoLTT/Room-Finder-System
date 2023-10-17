//package com.roomfindingsystem.controller;
//
//
//import com.roomfindingsystem.config.Role;
//import com.roomfindingsystem.entity.UserEntity;
//import com.roomfindingsystem.service.UserService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//import java.time.LocalDate;
//
//@Controller
//
//public class OtpController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @RequestMapping(value = "otp-check", method = RequestMethod.GET)
//    public String indexOtp() {
//        return "otpConfirm";
//    }
//
//    @RequestMapping(value = "confirm-otp", method = RequestMethod.POST)
//
//    public String checkOtp(HttpSession session, @RequestParam("otp") String otp, Model model) {
//        String otpRegister = (String) session.getAttribute("otp-register");
//        if (otp.equals(otpRegister)) {
//            UserEntity userEntity = new UserEntity();
//            userEntity.setUserId((Integer) session.getAttribute("userid"));
//            userEntity.setEmail((String) session.getAttribute("email"));
//            userEntity.setFirstName((String) session.getAttribute("firstname"));
//            userEntity.setLastName((String) session.getAttribute("lastname"));
//            userEntity.setDob((LocalDate) session.getAttribute("dob"));
//            userEntity.setPhone((String) session.getAttribute("phone"));
//            userEntity.setGender((Boolean) session.getAttribute("gender"));
//            userEntity.setPassword(passwordEncoder.encode((String) session.getAttribute("password")));
//            String role = (String) session.getAttribute("role");
//            if (role != null) {
//                userEntity.setRoleId(String.valueOf(Role.LANDLORD));
//            } else {
//                userEntity.setRoleId(String.valueOf(Role.USER));
//            }
//            userEntity.setCreatedDate(null);
//            userEntity.setAddressId(1);
//            userEntity.setFacebookId(null);
//            userEntity.setGmailId(null);
//            userEntity.setImageLink(null);
//            userEntity.setLastModifiedDate(null);
//            userService.saveUser(userEntity);
//            return "edirect:/";
//        }
//        model.addAttribute("mess","OTP is not correct! Please check your email.");
//
//        return "otpConfirm";
//    }
//}
