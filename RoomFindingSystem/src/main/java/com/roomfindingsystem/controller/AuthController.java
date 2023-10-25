package com.roomfindingsystem.controller;


import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.sbgooogle.GooglePojo;
import com.roomfindingsystem.sbgooogle.GoogleUtils;
import com.roomfindingsystem.service.UserService;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;



@Controller
public class AuthController {
    @GetMapping("/login")
    public String showLogin(){
        return "auth/login";
//        return "login";
    }

//    @RequestMapping("/n")
//    public String dinhvan(Model model){
//        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        model.addAttribute("currentUserName", currentUserName);
//        return "home";
//    }


    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    @Lazy
    private UserService userService;

    @RequestMapping("/login-google")
    public String loginGoogle(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        System.out.println("-----CALL TO THIS-----");
        if (code == null || code.isEmpty()) {
            return "redirect:/login?google=error";
        }

        // Kiểm tra xem tài khoản Google đã tồn tại trong bảng user chưa
//        Optional<UserEntity> existingUser = userService.findByEmail(googlePojo.getEmail()); // Thay thế bằng phương thức phù hợp của userService
//        System.out.println(existingUser.isEmpty()); ///1
        //TODO cật nhật thông tin ở đoạn này...
//        if (existingUser == null|| existingUser.isEmpty()) {
//            // Nếu tài khoản Google chưa có, thêm tài khoản mới vào bảng user
//            UserEntity newUser = new UserEntity();
//            newUser.setEmail(googlePojo.getEmail()); // Sử dụng email làm tên đăng nhập
////            newUser.setRoleId(1); // Gán vai trò mặc định (có thể thay đổi)
//            newUser.setPassword("aaaaaaa");
//            userService.save(newUser);
//        }

//        System.out.println("ok");

        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        System.out.println("DEBUG");
        return "redirect:/test";
    }



//    @RequestMapping("/user")
//    public String user() {
//        return "user";
//    }
//    @RequestMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
//    @RequestMapping("/403")
//    public String accessDenied() {
//        return "403";
//    }


}
