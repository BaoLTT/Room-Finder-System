package com.roomfindersystem.controller.landlord;

import com.roomfindersystem.dto.FeedbackDto;
import com.roomfindersystem.dto.FeedbackDtoAdmin;
import com.roomfindersystem.dto.HouseLandlordVo;
import com.roomfindersystem.entity.HousesEntity;
import com.roomfindersystem.entity.UserEntity;
import com.roomfindersystem.service.FeedbackService;
import com.roomfindersystem.service.HouseLandlordService;
import com.roomfindersystem.service.HouseService;
import com.roomfindersystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/landlord")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private HouseLandlordService houseLandlordService;

    @GetMapping("/feedback")
    public String getAllFeedback(@RequestParam(name = "star", required = false, defaultValue = "0") int star,
                                 @RequestParam(name = "houseId", required = false, defaultValue="0") int houseId,
                                 @RequestParam(name = "status", required = false, defaultValue = "true, false") List<Boolean> status,
                                 ModelMap model, HttpServletRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();

        List<HousesEntity> listHouse = new ArrayList<>();
        //sưửa đoạn này
        listHouse = houseService.getHouseIdByUserId(user.getUserId());

        List<FeedbackDto> feedbacks;

        if(houseId==0){
            houseId=listHouse.get(0).getHouseId();
        }
        if (star==0){
            feedbacks = feedbackService.getFeedbackByHouseId(houseId, status);
        } else {
            feedbacks = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);
        }



        model.addAttribute("request",request);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("star", star);
        model.addAttribute("status", status);
        model.addAttribute("houseId", houseId);
        model.addAttribute("listHouse", listHouse);
        return "landlord/landlordFeedback";

    }
}
