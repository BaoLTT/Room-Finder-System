package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.entity.TypeHouseEntity;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String findAll(Model model, HttpSession httpSession, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if(!user.getRoleId().equals("LANDLORD")){
            return "redirect:/login";
        }
        List<HouseLandlordVo> listHouse = new ArrayList<>();
        listHouse = houseLandlordService.findHouseByUser(user.getUserId());
        model.addAttribute("house",listHouse);
        return "landlord/managerHouse";
    }

    @GetMapping("/add")
    public String addHouse(Model model,HttpSession httpSession){
        HouseLandlordVo house = new HouseLandlordVo();

        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();
        model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listService",listService);
        return "landlord/managerAdd";
    }

    @GetMapping("/edit/{houseid}")
    public String detailHouse(@PathVariable Integer houseid,Model model, HttpSession httpSession,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if(!user.getRoleId().equals("LANDLORD")){
            return "redirect:/login";
        }
        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();

        HouseLandlordVo  house = houseLandlordService.findHouseByID(houseid);
        System.out.println(house.getListImage().get(0).getImageLink());
        List<String> listChecked = house.getService();
        System.out.println(listChecked);
                model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listChecked",listChecked);
        model.addAttribute("listService",listService);
        return "landlord/managerDetail";
    }

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute(name = "house") HouseLandlordVo house, @RequestParam("file") MultipartFile[] files,HttpServletRequest request) throws IOException {
        AddressEntity address = new AddressEntity("a",house.getAddressDetail().trim(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
        int addressID = addressService.insertAddress(address);
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        house.setUserID(user.getUserId());
        house.setCreatedBy(user.getUserId());
        house.setLastModifiedBy(user.getUserId());
        house.setStatus(2);
        //Set mặc định là đang xử lý
        houseManagerService.insertHouse(house,addressID,files);
        return  "redirect:/manager";
    }

    @PostMapping("/update")
    public String updateHouse(@ModelAttribute(name = "house") HouseLandlordVo house,@RequestParam("file") MultipartFile[] files,@RequestParam(name = "service", required = false,defaultValue = "0") List<Integer> service, Model model, HttpSession httpSession,HttpServletRequest request) throws IOException {
        if(house.getProvinceID()==0){
            Optional<AddressEntity> newAddress = addressService.findbyId(house.getAddress());
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
            addressService.updateAddress(address,house.getAddress());
        }else{
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
            addressService.updateAddress(address,house.getAddress());
        }
        System.out.println(house.getHouseID());
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        house.setUserID(user.getUserId());
        house.setCreatedBy(user.getUserId());
        house.setLastModifiedBy(user.getUserId());
        houseManagerService.updateHouse(house,house.getHouseID(),service,files);

        return  "redirect:/manager";
    }

    @GetMapping("/deleteImage/{houseId}/{imageId}")
    public String deleteImage(@PathVariable Integer houseId,@PathVariable Integer imageId,Model model, HttpSession httpSession){
        houseManagerService.deleteImageById(imageId);
        return "redirect:/manager/edit/" + houseId;
    }
}
