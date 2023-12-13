package com.roomfindingsystem.controller.admin;


import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class  AdminDashboardController {
    @Autowired
    HouseService houseService;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    NewsService newsService;

    @Autowired
    ReportService reportService;
    @GetMapping("/dashboard")
    public String getDashboard(Model model, HttpServletRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();

        //         Lưu user vào session

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

//        if(!user.getRoleId().equals("ADMIN") && !user.getRoleId().equals("SUPER_ADMIN")){
//            return "redirect:/login";
//        }
        model.addAttribute("numberOfHouses", houseService.countHousesInAdmin());
        model.addAttribute("numberOfUsers", userService.countUserInAdmin());
        model.addAttribute("numberOfNews", newsService.countNews());
        model.addAttribute("numberOfReports", reportService.countReports());
        model.addAttribute("roomStatusDto", roomService.getRoomStatusInAdminDashboard());

        List<Integer> statusList = new ArrayList<>();
        statusList.add(1);  statusList.add(0);

        model.addAttribute("statusList", statusList);

        return "admin/adminDashboard";
    }

    @PostMapping("/updateStatusDate")
    public String updateStatusDate(@RequestParam int roomId, @RequestParam String status,
                                   Model model) {
        try {
            int statusId = 1;
            if(Objects.equals(status, "Còn trống")) {
                statusId = 1;
            } else if (Objects.equals(status, "Hết phòng")) {
                statusId = 0;
            }
            roomService.updateStatusDate(roomId, statusId);
        } catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console

            // Thêm thông báo lỗi cho người dùng thông qua Model
            model.addAttribute("error", "Có lỗi xảy ra khi cập nhật statusUpdateDate.");

            // Trả về trang dashboard với thông báo lỗi
            return "admin/adminDashboard";
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/test")
    public String test(){
        return "admin/charts-chartjs";
    }
}
