package com.roomfindersystem.controller.admin;

import com.roomfindersystem.dto.FeedbackDto;
import com.roomfindersystem.dto.FeedbackDtoAdmin;
import com.roomfindersystem.entity.UserEntity;
import com.roomfindersystem.service.FeedbackService;
import com.roomfindersystem.service.UserService;
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
        return "admin/feedback-manager";

    }

    @GetMapping("/updateTrue")
    public String getTrue(@RequestParam(name = "fid", required = false) int fid,
                                 ModelMap model) {
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
