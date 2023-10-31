package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.FeedbackService;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.vo.FeedbackDto;
import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.ServiceDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HouseController {
    @Autowired
    HouseService houseService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "houseDetail")
    public String index(Model model) {
//        model.addAttribute("User", new UserEntity());
        return "templates";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getAllHouse(@RequestParam("id") Integer houseId, ModelMap model) {
        List<HouseDto> houseDto = houseService.getHouseDetail(houseId);
        System.out.printf(houseDto.toString());


        model.addAttribute("HousesEntity", houseDto);

        List<ServiceDto> listService= houseService.getServiceById(houseId);
        model.addAttribute("HouseService", listService);
//        houseDto.ifPresent(user -> model.addAttribute("HousesEntity", user));
        // get Image
        List<HouseImageLink> listsImage = houseService.getImageById(houseId);
        System.out.println(listsImage.toString());
        model.addAttribute("HousesImages", listsImage);



        //nghia code
        List<FeedbackDto> feedbacks = feedbackService.getFeedbackByHouseId(houseId);
        model.addAttribute("feedbacks", feedbacks);

        //lấy ra tên của user hiện tại -> lấy ra user hiện tại
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(currentUserName).get();

        //set houseId và userid cho feedback
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setHouseId(houseId);
        feedbackEntity.setMemberId(user.getUserId());

        //lấy ra số lượng comment của user hiện tai ở bài vieest này
        int count = 0;
        List<FeedbackEntity> feedbackEntities= feedbackService.getFeedbackEntityByUid(houseId, user.getUserId());
        count = feedbackEntities.size();

        if(count>0)
        feedbackEntity = feedbackEntities.get(0);




        model.addAttribute("feedbackEntity", feedbackEntity);
        model.addAttribute("user", user);
        model.addAttribute("count", count);


        return "detail";
    }

    // add feedback
    @PostMapping(value="detail")
    public String addFeedback(@Valid @ModelAttribute("feedback") FeedbackEntity feedbackEntity, BindingResult bindingResult){
        LocalDate currentDate = LocalDate.now();
        feedbackEntity.setCreatedDate(currentDate);
        int houseId = feedbackEntity.getHouseId();
        if(feedbackService.getFeedbackEntityByUid(houseId, feedbackEntity.getMemberId()).size()==0){
            feedbackService.save(feedbackEntity);
        }else{
            feedbackEntity.setFeedbackId(feedbackService.getFeedbackEntityByUid(houseId, feedbackEntity.getMemberId()).get(0).getFeedbackId());
            feedbackService.save(feedbackEntity);
        }
        return "redirect:/detail?id=" + houseId + "#reviews";
    }

    @GetMapping(value = "deleteHouseCmt")
    public String deleteComment(@RequestParam("houseId") int houseId){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(houseId);
        System.out.println("houseId");
        UserEntity user = userService.findByEmail(currentUserName).get();
        feedbackService.deleteByHouseIdAndMemberId(houseId, user.getUserId());
        return "redirect:/detail?id=" + houseId + "#reviews";
    }
}
