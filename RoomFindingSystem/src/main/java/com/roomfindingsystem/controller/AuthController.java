package com.roomfindingsystem.controller;


import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.sbgooogle.GooglePojo;
import com.roomfindingsystem.sbgooogle.GoogleUtils;
import com.roomfindingsystem.service.UserService;


import com.roomfindingsystem.service.impl.GcsService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Controller
public class AuthController {
    @GetMapping("/login")
    public String showLogin(){
        return "auth/login1";
//        return "login";
    }

    @RequestMapping("/ok")
    public String dinhvan(Model model){
//        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        model.addAttribute("currentUserName", currentUserName);
        return "test";
    }


    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    GcsService gcsService;

    @RequestMapping("/login-google")
    public String loginGoogle(HttpServletRequest request, Model model) throws Exception {
        String code = request.getParameter("code");
        System.out.println("-----CALL TO THIS-----");
        if (code == null || code.isEmpty()) {
            return "redirect:/login?google=error";
        }

        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);




//         Kiểm tra xem tài khoản Google đã tồn tại trong bảng user chưa
        Optional<UserEntity> existingUser = userService.findByEmail(googlePojo.getEmail()); // Thay thế bằng phương thức phù hợp của userService
//        System.out.println(existingUser.isEmpty()); ///1

//        TODO cật nhật thông tin ở đoạn này...
        if (existingUser.isEmpty()) {
            // Nếu tài khoản Google chưa có, thêm tài khoản mới vào bảng user
            UserEntity newUser = new UserEntity();
            newUser.setEmail(googlePojo.getEmail());
            newUser.setFirstName(googlePojo.getFamily_name());
            newUser.setLastName(googlePojo.getGiven_name());
//            newUser.setImageLink(googlePojo.getPicture());
            byte[] imageBytes = googlePojo.getPicture().getBytes();
            gcsService.uploadImage("rfs_bucket", "User/user_" +googlePojo.getId()+".jpg", imageBytes);
            newUser.setImageLink("/rfs_bucket/User/"+"user_"+googlePojo.getId()+".jpg");

            newUser.setGmailId(accessToken);
            newUser.setRoleId("MEMBER");
            //setStatus == 1
            newUser.setUserStatusId(1);
            model.addAttribute("newUser", newUser);

//            userService.save(newUser);
            return "auth/addInfoGoogle";
        }


        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        List<GrantedAuthority> authorities = new ArrayList<>(userDetail.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + existingUser.get().getRoleId()));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        System.out.println("DEBUG");
        return "redirect:/";
    }



//    @RequestMapping("/user")
//    public String user() {
//        return "user";
//    }
//    @RequestMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
    @RequestMapping("/403")
    public String accessDenied(HttpServletRequest request, ModelMap model) {
        model.addAttribute("request",request);
        return "403";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        SecurityContextHolder.getContext().setAuthentication(null); // Đăng xuất người dùng
        return "redirect:/?logout"; // Chuyển hướng đến trang đăng nhập sau khi đăng xuất
    }

    @PostMapping("/loginAfterAddInfo")
    public String AddInfo(Model model, @Valid @ModelAttribute("newUser") UserEntity newUser, HttpServletRequest request) throws IOException {
        userService.save(newUser);
        System.out.println("ok");

        GooglePojo googlePojo = googleUtils.getUserInfo(newUser.getGmailId());
        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        List<GrantedAuthority> authorities = new ArrayList<>(userDetail.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + newUser.getRoleId()));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        System.out.println("DEBUG");
        return "redirect:/";
    }

}
