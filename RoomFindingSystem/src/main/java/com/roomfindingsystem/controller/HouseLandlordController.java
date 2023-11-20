package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.entity.TypeHouseEntity;
import com.roomfindingsystem.reponsitory.TypeHouseRepository;
import com.roomfindingsystem.service.HouseLandlordService;
import com.roomfindingsystem.service.ServiceDetailService;
import com.roomfindingsystem.vo.HouseLandlordVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class HouseLandlordController {
    @Autowired
    private HouseLandlordService houseLandlordService;
    @Autowired
    TypeHouseRepository typeHouseRepository;
    @Autowired
    ServiceDetailService serviceDetailService;

    @GetMapping("")
    public String findAll(Model model, HttpSession httpSession){
        List<HouseLandlordVo> listHouse = new ArrayList<>();
        int userId = 1;
        listHouse = houseLandlordService.findHouse(userId);
        model.addAttribute("house",listHouse);
        return "managerHouse";
    }

    @GetMapping("/edit/{houseid}")
    public String detailHouse(@PathVariable Integer houseid,Model model, HttpSession httpSession){
        List<TypeHouseEntity> listType = typeHouseRepository.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();

        HouseLandlordVo  house = houseLandlordService.findHouseByID(houseid);
        List<String> listChecked = house.getService();
        System.out.println(listChecked);
                model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listChecked",listChecked);
        model.addAttribute("listService",listService);
        return "managerDetail";
    }
}
