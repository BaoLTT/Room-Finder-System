package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.entity.*;

import com.roomfindingsystem.repository.TypeHouseRepository;
import com.roomfindingsystem.repository.UserRepository;
import com.roomfindingsystem.service.AddressService;
import com.roomfindingsystem.service.HouseLandlordService;
import com.roomfindingsystem.service.HouseManagerService;

import com.roomfindingsystem.service.ServiceDetailService;
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
@RequestMapping("/admin")
public class AdminManageHouseController {
    private HouseManagerService houseManagerService;

    public AdminManageHouseController(HouseManagerService houseManagerService){
        this.houseManagerService = houseManagerService;
    }
    @Autowired
    TypeHouseRepository typeHouseRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceDetailService serviceDetailService;
    @Autowired
    HouseLandlordService houseLandlordService;



    @GetMapping("/house-manager")
    public String viewHomepage(final Model model, HttpSession httpSession){
        List<HouseLandlordVo> houseList = houseLandlordService.getAllHouse();
        model.addAttribute("houses", houseList);
        //entries từ 0 đến 5 vào jquery.dataTables.min.js" tìm entries sửa display = 5
        return "admin/house-manager";
    }

    @GetMapping("/house-manager/delete/{houseid}")
    public String deleteHouse(@PathVariable Integer houseid,HttpSession httpSession){
        houseManagerService.deleteHouse(houseid);
        return "redirect:/admin/house-manager";
    }

    @GetMapping("/house-manager/detail/{houseid}")
    public String updateHouse(@PathVariable Integer houseid,final Model model,HttpSession httpSession){
        List<TypeHouseEntity> listType = typeHouseRepository.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();

        HouseLandlordVo  house = houseLandlordService.findHouseByID(houseid);
        List<String> listChecked = house.getService();
        System.out.println(listChecked);
        model.addAttribute("house",house);
        model.addAttribute("listType",listType);
        model.addAttribute("listChecked",listChecked);
        model.addAttribute("listService",listService);
        return "admin/house-manager-detail";
    }
    @PostMapping("/house-manager/update")
    public String updateHouse(@ModelAttribute("house") HouseLandlordVo house,@RequestParam("file") MultipartFile[] files, @RequestParam(name = "service", required = false,defaultValue = "0") List<Integer> service, MultipartFile[] images, Model model, HttpSession httpSession) throws IOException {
        if(house.getProvinceID()==0){
            Optional<AddressEntity> newAddress = addressService.findbyId(house.getAddress());
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
            addressService.updateAddress(address,house.getAddress());
        }else{
            AddressEntity address = new AddressEntity("a",house.getAddressDetail(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
            addressService.updateAddress(address,house.getAddress());
        }
        System.out.println(house.getHouseID());
        System.out.println(service);

        houseManagerService.updateHouse(house,house.getHouseID(),service,files);

        return "redirect:/admin/house-manager";
    }

    @GetMapping("/house-manager/add")
    public String addHouse(final Model model,HttpSession httpSession){
        List<UserEntity> listUser = new ArrayList<>();
        listUser = userRepository.findAll();
        List<TypeHouseEntity> listType = typeHouseRepository.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listType",listType);
        model.addAttribute("listService",listService);
        HouseLandlordVo house = new HouseLandlordVo();
        model.addAttribute("house",house);
        return "admin/house-manager-add";
    }

    @PostMapping("/house-manager/save")
    public String saveHouse(@ModelAttribute(name = "house") HouseLandlordVo house, @RequestParam("file") MultipartFile[] files, Model model, HttpSession httpSession) throws IOException {
        AddressEntity address = new AddressEntity("a",house.getAddressDetail().trim(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
        int addressID = addressService.insertAddress(address);
        houseManagerService.insertHouse(house,addressID,files);
        return  "redirect:/admin/house-manager";
    }

    @GetMapping("/house-manager/deleteImage/{houseId}/{imageId}")
    public String deleteImage(@PathVariable Integer houseId,@PathVariable Integer imageId,Model model, HttpSession httpSession){
        houseManagerService.deleteImageById(imageId);
        return "redirect:/admin/house-manager/detail/" + houseId;
    }



}
