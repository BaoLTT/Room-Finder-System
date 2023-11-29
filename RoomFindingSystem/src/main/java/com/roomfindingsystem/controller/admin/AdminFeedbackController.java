package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/feedback")
    public String getAllFeedback(@RequestParam(name = "id", required = false, defaultValue = "1") int houseId,
                              @RequestParam(name = "star", required = false, defaultValue = "0") int star,
                              @RequestParam(name = "status", required = false, defaultValue = "true, false") List<Boolean> status,
                              ModelMap model) {
        List<FeedbackDto> feedbacks;
        //nghia code
        if (star==0){
            feedbacks = feedbackService.getFeedbackByHouseId(houseId, status);
        } else {
            feedbacks = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);
        }




        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("star", star);
        model.addAttribute("status", status);
        model.addAttribute("houseId", houseId);
        return "admin/feedback-manager";

    }

    @GetMapping("/updateTrue")
    public String getTrue(@RequestParam(name = "fid", required = false) int fid,
                          @RequestParam(name = "houseId", required = false, defaultValue = "1") int houseId,
                                 ModelMap model) {
        feedbackService.updateStatusToTrue(fid);
        return "redirect:/admin/feedback?id="+houseId  ;

    }

    @GetMapping("/updateFalse")
    public String getFalse(@RequestParam(name = "fid", required = false) int fid,
                          @RequestParam(name = "houseId", required = false, defaultValue = "1") int houseId,
                          ModelMap model) {
        feedbackService.updateStatusToFalse(fid);
        return "redirect:/admin/feedback?id="+houseId  ;

    }

}
