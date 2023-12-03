package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;
import com.roomfindingsystem.service.impl.GcsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.service.FeedbackService;
import com.roomfindingsystem.service.HouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
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


    @Autowired
    GcsService gcsService;

    LocalDate currentDate = LocalDate.now();



    @RequestMapping(value = "houseDetail")
    public String index(Model model) {
//        model.addAttribute("User", new UserEntity());
        return "templates";
    }

    @RequestMapping(value = "{housename}", method = RequestMethod.GET)
    public String getAllHouse(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "id", required = false, defaultValue = "1") int houseId,
                              @RequestParam(name = "star", required = false, defaultValue = "0") int star,
                              ModelMap model, HttpServletRequest request) {

        try {
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
            List<Boolean> statuss = new ArrayList<>();
            statuss.add(true);


            List<FeedbackDto> feedbacks = new ArrayList<>();
            //nghia code
            if (star==0){
                feedbacks = feedbackService.getFeedbackByHouseId(houseId, statuss);
            } else {
                feedbacks = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, statuss);
            }
//        List<FeedbackDto> feedbacks = feedbackService.getFeedbackByHouseId(houseId);
            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("star", star);

            //lấy ra tên của user hiện tại -> lấy ra user hiện tại
            UserEntity user = null;
            try {
                String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
                user = userService.findByEmail(currentUserName).get();

                //set houseId và userid cho feedback
                FeedbackEntity feedbackEntity = new FeedbackEntity();
                feedbackEntity.setHouseId(houseId);
                feedbackEntity.setMemberId(user.getUserId());
                int count = 0;
                List<FeedbackEntity> feedbackEntities= feedbackService.getFeedbackEntityByUid(houseId, user.getUserId());
                count = feedbackEntities.size();


                if(count>0) feedbackEntity = feedbackEntities.get(0);


                //set houseId và userid cho feedback
                ReportEntity reportEntity = new ReportEntity();

                //lấy ra số lượng report của user hiện tai ở bài vieest này
                int countReport = 0;
                List<ReportEntity> reportEntities= reportService.getReportEntityByUid(houseId, user.getUserId());
                countReport = reportEntities.size();
                int check = 1;// cho bao cao

                if(countReport>0) {
                    reportEntity = reportEntities.get(0);
                    String status = reportEntity.getReportStatus();

                    if(reportEntity.getReportStatus().equals("Chờ Xử Lý")
                    ){
                        check = 0; //ko cho bao cao nua
                    }
                }
                if(check==1)
                    reportEntity=new ReportEntity();
                reportEntity.setHouseid(houseId);
                reportEntity.setUserid(user.getUserId());





                model.addAttribute("feedbackEntity", feedbackEntity);

                model.addAttribute("count", count);
                model.addAttribute("reportEntity", reportEntity);
                model.addAttribute("countReport", countReport);
                model.addAttribute("checkReport", check);
            }
            catch(Exception e){

            }
            //lấy ra số lượng comment của user hiện tai ở bài vieest này


            //baoltt code
            List<RoomHouseDetailDto> roomHouseDetailDtos = roomService.viewRoomInHouse(houseId);
            model.addAttribute("roomList", roomHouseDetailDtos);
            model.addAttribute("roomService", roomService);
            model.addAttribute("houseLocation", houseService.getHouseById(houseId));
            model.addAttribute("key_map", gcsService.getMapKey());
            model.addAttribute("request",request);
            model.addAttribute("user", user);

            System.out.println(roomHouseDetailDtos.toString());



            return "housedetail";


        } catch (Exception exception) {
            exception.printStackTrace();
            model.addAttribute("request",request);
            return "404";
        }

    }

    // add feedback
    @PostMapping(value="detail")
    public String addFeedback(@Valid @ModelAttribute("feedback") FeedbackEntity feedbackEntity, BindingResult bindingResult){

        feedbackEntity.setCreatedDate(currentDate);
        feedbackEntity.setStatus(true);
        int houseId = feedbackEntity.getHouseId();
        int sum =0;
        if(feedbackService.getFeedbackEntityByUid(houseId, feedbackEntity.getMemberId()).size()==0){
            feedbackService.save(feedbackEntity);



            //get house -> get star avg, get count, (avg*count + star)/(count+1)
        }else{
            feedbackEntity.setFeedbackId(feedbackService.getFeedbackEntityByUid(houseId, feedbackEntity.getMemberId()).get(0).getFeedbackId());
            feedbackService.save(feedbackEntity);
        }
        List<Boolean> statuss = new ArrayList<>();
        statuss.add(true);
        List<FeedbackDto> feedbackDtoList = feedbackService.getFeedbackByHouseId(houseId, statuss);
        for(int i=0; i<feedbackDtoList.size(); i++){
            sum+=feedbackDtoList.get(i).getStar();
        }
        double avg = (double)sum / (double)feedbackDtoList.size();
        double roundedAvg = round(avg, 1);
        houseService.updateStar(roundedAvg, houseId);
        return "redirect:/detail?id=" + houseId + "#reviews";
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @GetMapping(value = "deleteHouseCmt")
    public String deleteComment(@RequestParam("houseId") int houseId){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(houseId);
        System.out.println("houseId");
        UserEntity user = userService.findByEmail(currentUserName).get();
        feedbackService.deleteByHouseIdAndMemberId(houseId, user.getUserId());
        int sum =0;
        List<Boolean> statuss = new ArrayList<>();
        statuss.add(true);
        List<FeedbackDto> feedbackDtoList = feedbackService.getFeedbackByHouseId(houseId, statuss);
        for(int i=0; i<feedbackDtoList.size(); i++){
            sum+=feedbackDtoList.get(i).getStar();
        }
        double avg = (double)sum / (double)feedbackDtoList.size();
        double roundedAvg = round(avg, 1);
        houseService.updateStar(roundedAvg, houseId);
        return "redirect:/detail?id=" + houseId + "#reviews";
    }

    @PostMapping(value="report")
    public String addReport(@Valid @ModelAttribute("report") ReportEntity reportEntity, BindingResult bindingResult){

        reportEntity.setCreatedDate(currentDate.plusDays(1));
        int houseId = reportEntity.getHouseid();
        reportEntity.setReportStatus("Chờ Xử Lý");
        List<ReportEntity> reportEntities = reportService.getReportEntityByUid(houseId, reportEntity.getUserid());
        //update
        if(reportEntities.size()>0&&reportEntities.get(0).getReportStatus().equals("Chưa Xử Lý")){
            reportEntity.setReportid(reportService.getReportEntityByUid(houseId, reportEntity.getUserid()).get(0).getReportid());
            reportService.save(reportEntity);
        }else{//update
//            reportEntity.setReportid(reportService.getReportEntityByUid(houseId, reportEntity.getUserid()).get(0).getReportid());
            reportService.save(reportEntity);
        }
        //lưu mới
//        if(reportService.getReportEntityByUid(houseId, reportEntity.getUserid()).size()==0){
//            reportService.save(reportEntity);
//        }else{//update
//            reportEntity.setReportid(reportService.getReportEntityByUid(houseId, reportEntity.getUserid()).get(0).getReportid());
//            reportService.save(reportEntity);
//        }
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
