package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.entity.TypeHouseEntity;

import com.roomfindingsystem.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class HouseLandlordController {
    @Autowired
    private HouseLandlordService houseLandlordService;
    @Autowired
    HouseTypeService houseTypeService;
    @Autowired
    ServiceDetailService serviceDetailService;

    @Autowired
    AddressService addressService;

    @Autowired
    HouseManagerService houseManagerService;

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
        List<TypeHouseEntity> listType = houseTypeService.findAll();
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

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute(name = "house") HouseLandlordVo house, Model model, HttpSession httpSession){
//        System.out.println(house.getHouseName());
//        System.out.println(house.getTypeHouseID());
//        System.out.println(house.getDescription());
//        System.out.println(house.getAddressDetail());
//        System.out.println(house.getProvinceID());
//        System.out.println(house.getDistrictID());
//        System.out.println(house.getWardID());
//        System.out.println(house.getService());
//        System.out.println(house.getStatus());
        int userid= 1;
        LocalDate createdDate = LocalDate.now();
        AddressEntity address = new AddressEntity("a",house.getAddressDetail().trim(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
        int addressID = addressService.insertAddress(address);
        HousesEntity newHouse = new HousesEntity(house.getHouseName().trim(),house.getDescription().trim(),createdDate,userid,createdDate,userid,addressID,house.getTypeHouseID(),userid,house.getStatus());
        houseManagerService.insertHouse(newHouse);
        return  "redirect:/manager";
    }
}
