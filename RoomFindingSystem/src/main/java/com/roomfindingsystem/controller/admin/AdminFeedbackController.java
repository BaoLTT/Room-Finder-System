package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackDtoAdmin;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.FeedbackService;
import com.roomfindingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminFeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    UserService userService;

    @GetMapping("/feedback")
    public String getAllFeedback(@RequestParam(name = "star", required = false, defaultValue = "0") int star,
                              @RequestParam(name = "status", required = false, defaultValue = "true, false") List<Boolean> status,
                              ModelMap model) {
        try{
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user = userService.findByEmail(email).get();
            List<FeedbackDtoAdmin> feedbacks;


            //nghia code
            if (star==0){
                feedbacks = feedbackService.getFeedback(status);
            } else {
                feedbacks = feedbackService.getFeedbackByStar(star, status);
            }



            model.addAttribute("user", user);
            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("star", star);
            model.addAttribute("status", status);
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console

            // Thêm thông báo lỗi cho người dùng thông qua Model
            model.addAttribute("error", "Có lỗi xảy ra khi cập nhật statusUpdateDate.");

            // Trả về trang dashboard với thông báo lỗi
            return "404Admin";
        }

        return "admin/feedback-manager";

    }

    @GetMapping("/updateTrue")
    public String getTrue(@RequestParam(name = "fid", required = false) int fid, ModelMap model) {
        feedbackService.updateStatusToTrue(fid);
        return "redirect:/admin/feedback"  ;

    }

    @GetMapping("/updateFalse")
    public String getFalse(@RequestParam(name = "fid", required = false) int fid,

                          ModelMap model) {
        feedbackService.updateStatusToFalse(fid);
        return "redirect:/admin/feedback"  ;

    }

}
