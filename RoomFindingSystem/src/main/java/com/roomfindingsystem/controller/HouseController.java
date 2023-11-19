package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;
import jakarta.validation.Valid;
import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.service.FeedbackService;
import com.roomfindingsystem.service.HouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static net.minidev.asm.ConvertDate.convertToDate;

@Controller
public class HouseController {
    @Autowired
    HouseService houseService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;
    @Autowired
    ReportService reportService;



    @RequestMapping(value = "houseDetail")
    public String index(Model model) {
//        model.addAttribute("User", new UserEntity());
        return "templates";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getAllHouse(@RequestParam(name = "id", required = false, defaultValue = "1") int houseId, ModelMap model) {
        List<HouseDto> houseDto = houseService.getHouseDetail(houseId);
        System.out.printf(houseDto.toString());


        model.addAttribute("HousesEntity", houseDto);
        System.out.println(houseDto);

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

        try {
            String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user = userService.findByEmail(currentUserName).get();

            //set houseId và userid cho feedback
            FeedbackEntity feedbackEntity = new FeedbackEntity();
            feedbackEntity.setHouseId(houseId);
            feedbackEntity.setMemberId(user.getUserId());
            int count = 0;
            List<FeedbackEntity> feedbackEntities= feedbackService.getFeedbackEntityByUid(houseId, user.getUserId());
            count = feedbackEntities.size();

            if(count>0)
                feedbackEntity = feedbackEntities.get(0);

            //set houseId và userid cho feedback
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setHouseid(houseId);
            reportEntity.setUserid(user.getUserId());

            //lấy ra số lượng comment của user hiện tai ở bài vieest này
            int countReport = 0;
            List<ReportEntity> reportEntities= reportService.getReportEntityByUid(houseId, user.getUserId());
            countReport = reportEntities.size();

            if(countReport>0)
                reportEntity = reportEntities.get(0);




            model.addAttribute("feedbackEntity", feedbackEntity);
            model.addAttribute("user", user);
            model.addAttribute("count", count);
            model.addAttribute("reportEntity", reportEntity);
            model.addAttribute("countReport", countReport);
        }
        catch(Exception e){

        }





        //lấy ra số lượng comment của user hiện tai ở bài vieest này


        //baoltt code
        List<RoomHouseDetailDto> roomHouseDetailDtos = roomService.viewRoomInHouse(houseId);
        model.addAttribute("roomList", roomHouseDetailDtos);



        return "housedetail";
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

    @PostMapping(value="report")
    public String addReport(@Valid @ModelAttribute("report") ReportEntity reportEntity, BindingResult bindingResult){
//        LocalDate currentDate = LocalDate.now();
//        reportEntity.setCreatedDate((Date)currentDate);
        int houseId = reportEntity.getHouseid();
        //nếu số lượng feedback của người đó = 0 thì cho phép add thêm
        if(reportService.getReportEntityByUid(houseId, reportEntity.getUserid()).size()==0){
            reportService.save(reportEntity);
        }else{
            reportEntity.setReportid(reportService.getReportEntityByUid(houseId, reportEntity.getUserid()).get(0).getReportid());
            reportService.save(reportEntity);
        }
        return "redirect:/detail?id=" + houseId;
    }

    @GetMapping(value = "deleteReport")
    public String deleteReport(@RequestParam("houseId") int houseId){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(currentUserName).get();
        reportService.deleteByHouseIdAndMemberId(houseId, user.getUserId());
        return "redirect:/detail?id=" + houseId;
    }
}
