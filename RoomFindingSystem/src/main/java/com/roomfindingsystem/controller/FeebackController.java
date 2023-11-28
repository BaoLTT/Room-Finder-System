package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.FeedbackService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Controller
public class FeebackController {
    @Autowired
    private FeedbackService feedbackService;
    @RequestMapping(value = "feedback-list")
    public String getFeebackList(Model model) {
        List<FeedbackListAdminDto> list = feedbackService.getListFeedback();
        model.addAttribute("feedbackList", list);
        return "feedback-list-admin";
    }

    @RequestMapping(value = "feedback-list-landlord")
    public String getFeebackListForLandLord(Model model, HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        List<FeedbackListAdminDto> list = feedbackService.getListFeedbackForLandLord(user.getUserId());
        model.addAttribute("feedbackList", list);
        return "feedback-list-landlord";
    }


}
