package com.roomfindingsystem.controller.landlord;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.entity.*;

import com.roomfindingsystem.service.*;

import com.roomfindingsystem.service.impl.GcsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/landlord")
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

    @Autowired
    UserService userService;
    @Autowired
    HouseService houseService;
    @Autowired
    GcsService gcsService;


    @GetMapping("/listHouse")
    public String findAll(Model model, HttpSession httpSession, HttpServletRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        System.out.println(user.getRoleId());
        List<HouseLandlordVo> listHouse = new ArrayList<>();
        listHouse = houseLandlordService.findHouseByUser(user.getUserId());
        model.addAttribute("house",listHouse);
        model.addAttribute("request",request);
        return "landlord/managerHouse";
    }

    @GetMapping("/add")
    public String addHouse(Model model,HttpSession httpSession,HttpServletRequest request){
        HouseLandlordVo house = new HouseLandlordVo();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        System.out.println(user.getRoleId());
        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();
        house.setLatitude(21.0130252);
        house.setLongitude(105.5239285);
        model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listService",listService);
        model.addAttribute("request",request);
        model.addAttribute("key_map", gcsService.getMapKey());
        return "landlord/managerAdd";
    }

    @GetMapping("/edit/{houseid}")
    public String detailHouse(@PathVariable Integer houseid,Model model, HttpSession httpSession,HttpServletRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        System.out.println(user.getRoleId());
        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();

        HouseLandlordVo  house = houseLandlordService.findHouseByID(houseid);
        List<String> listChecked = house.getService();
        System.out.println(listChecked);
                model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listChecked",listChecked);
        model.addAttribute("listService",listService);
        model.addAttribute("request",request);


        model.addAttribute("key_map", gcsService.getMapKey());
        model.addAttribute("houseLocation", houseService.getHouseById(houseid));
        return "landlord/managerDetail";
    }

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute(name = "house") HouseLandlordVo house, @RequestParam("file") MultipartFile[] files,HttpServletRequest request) throws IOException {
        AddressEntity address = new AddressEntity(house.getAddressDetail().trim().replaceAll("\\s+", " "),house.getProvinceID(),house.getDistrictID(),house.getWardID());
        int addressID = addressService.insertAddress(address);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        house.setUserID(user.getUserId());
        house.setCreatedBy(user.getUserId());
        house.setLastModifiedBy(user.getUserId());
        house.setStatus(1);



        houseManagerService.insertHouse(house,addressID,files);



        return  "redirect:/landlord/listHouse";

    }

    @PostMapping("/update")
    public String updateHouse(@ModelAttribute(name = "house") HouseLandlordVo house,@RequestParam("file") MultipartFile[] files,@RequestParam(name = "service", required = false,defaultValue = "0") List<Integer> service, Model model, HttpSession httpSession,HttpServletRequest request) throws IOException {
        if(house.getProvinceID()==0){
            Optional<AddressEntity> newAddress = addressService.findbyId(house.getAddress());
            AddressEntity address = new AddressEntity(house.getAddressDetail().trim().replaceAll("\\s+", " "),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
            addressService.updateAddress(address,house.getAddress());
        }else{
            AddressEntity address = new AddressEntity(house.getAddressDetail().trim().replaceAll("\\s+", " "),house.getProvinceID(),house.getDistrictID(),house.getWardID());
            addressService.updateAddress(address,house.getAddress());
        }
        System.out.println(house.getHouseID());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        house.setUserID(user.getUserId());
        house.setCreatedBy(user.getUserId());
        house.setLastModifiedBy(user.getUserId());
        houseManagerService.updateHouse(house,house.getHouseID(),service,files);




        return  "redirect:/landlord/listHouse";
    }

    @GetMapping("/deleteImage/{houseId}/{imageId}")
    public String deleteImage(@PathVariable Integer houseId,@PathVariable Integer imageId,Model model, HttpSession httpSession){
        houseManagerService.deleteImageById(imageId);
        return "redirect:/landlord/edit/" + houseId;
    }
}
