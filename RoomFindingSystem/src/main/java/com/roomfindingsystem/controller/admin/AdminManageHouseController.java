package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.entity.*;

import com.roomfindingsystem.repository.UserRepository;
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
@RequestMapping("/admin")
public class AdminManageHouseController {
    private HouseManagerService houseManagerService;

    public AdminManageHouseController(HouseManagerService houseManagerService){
        this.houseManagerService = houseManagerService;
    }
    @Autowired
    HouseTypeService houseTypeService;
    @Autowired
    AddressService addressService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceDetailService serviceDetailService;
    @Autowired
    HouseLandlordService houseLandlordService;

    @Autowired
    GcsService gcsService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;


    @GetMapping("/house-manager")
    public String viewHomepage(final Model model, HttpServletRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        System.out.println(user.getImageLink());
        List<HouseLandlordVo> houseList = houseLandlordService.getAllHouse();
        model.addAttribute("houses", houseList);
        model.addAttribute("user", user);
        //entries từ 0 đến 5 vào jquery.dataTables.min.js" tìm entries sửa display = 5


        return "admin/house-manager";
    }


    @PostMapping ("/house-manager/addType")
    public String addType(@RequestParam(name = "newType") String newType,HttpSession httpSession,HttpServletRequest request){
        TypeHouseEntity typeHouseEntity = new TypeHouseEntity();
        typeHouseEntity.setTypeName(newType.trim().replaceAll("\\s+", " "));
        typeHouseEntity.setCreatedDate(LocalDate.now());
        houseTypeService.addType(typeHouseEntity);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping ("/house-manager/addService")
    public String addService(@RequestParam(name = "newService") String newService,HttpSession httpSession,HttpServletRequest request){
        ServiceDetailEntity serviceDetailEntity = new ServiceDetailEntity();
        serviceDetailEntity.setServiceName(newService.trim().replaceAll("\\s+", " "));
        serviceDetailEntity.setCreateDate(LocalDate.now());
        serviceDetailService.save(serviceDetailEntity);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping ("/house-manager/deleteType")
    public String deleteType(@RequestParam(name = "deleteType") String deleteType,HttpSession httpSession,HttpServletRequest request){
        houseTypeService.deleteType(Integer.parseInt(deleteType));
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/house-manager/detail/{houseid}")
    public String updateHouse(@PathVariable Integer houseid,final Model model,HttpSession httpSession,HttpServletRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();

        HouseLandlordVo  house = houseLandlordService.findHouseByID(houseid);
        List<String> listChecked = house.getService();

        List<ServiceDetailEntity> listServiceNotUse = serviceDetailService.getServiceNotUse();
        List<TypeHouseEntity> listTypeNotUse = houseTypeService.findTypeNotUse();

        model.addAttribute("house",house);
        model.addAttribute("houseID",house.getHouseID());
        model.addAttribute("listType",listType);
        model.addAttribute("listChecked",listChecked);
        model.addAttribute("listService",listService);
        model.addAttribute("listServiceNotUse",listServiceNotUse);
        model.addAttribute("listTypeNotUse",listTypeNotUse);
        model.addAttribute("user", user);
        model.addAttribute("key_map", gcsService.getMapKey());

        return "admin/house-manager-detail";
    }
    @PostMapping("/house-manager/update")
    public String updateHouse(@ModelAttribute("house") HouseLandlordVo house,@RequestParam("file") MultipartFile[] files, @RequestParam(name = "service", required = false,defaultValue = "0") List<Integer> service, MultipartFile[] images, Model model, HttpSession httpSession,HttpServletRequest request)throws IOException {
        if(house.getProvinceID()==0){
            Optional<AddressEntity> newAddress = addressService.findbyId(house.getAddress());
            AddressEntity address = new AddressEntity("a",house.getAddressDetail().trim().replaceAll("\\s+", " "),newAddress.get().getProvinceId(),newAddress.get().getDistrictId(),newAddress.get().getWardId());
            addressService.updateAddress(address,house.getAddress());
        }else{
            AddressEntity address = new AddressEntity("a",house.getAddressDetail().trim().replaceAll("\\s+", " "),house.getProvinceID(),house.getDistrictID(),house.getWardID());
            addressService.updateAddress(address,house.getAddress());
        }
        System.out.println(house.getHouseID());
        System.out.println(service);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        house.setLastModifiedBy(user.getUserId());
        houseManagerService.updateHouse(house,house.getHouseID(),service,files);




        return "redirect:/admin/house-manager";
    }

    @GetMapping("/house-manager/add")
    public String addHouse(final Model model,HttpSession httpSession,HttpServletRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        List<UserEntity> listUser = new ArrayList<>();
        listUser = userRepository.findUserByRole("LANDLORD");
        List<TypeHouseEntity> listType = houseTypeService.findAll();
        List<ServiceDetailEntity> listService = serviceDetailService.getAllService();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listType",listType);
        model.addAttribute("listService",listService);
        HouseLandlordVo house = new HouseLandlordVo();
        house.setLatitude(21.0130252);
        house.setLongitude(105.5239285);
        model.addAttribute("house",house);

        model.addAttribute("user", user);

        model.addAttribute("key_map", gcsService.getMapKey());



        return "admin/house-manager-add";
    }

    @PostMapping("/house-manager/save")
    public String saveHouse(@ModelAttribute(name = "house") HouseLandlordVo house, @RequestParam("file") MultipartFile[] files, Model model, HttpSession httpSession, HttpServletRequest request) throws IOException {
        AddressEntity address = new AddressEntity("a",house.getAddressDetail().trim().replaceAll("\\s+", " ").trim(),house.getProvinceID(),house.getDistrictID(),house.getWardID());
        int addressID = addressService.insertAddress(address);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        house.setCreatedBy(user.getUserId());
        house.setLastModifiedBy(user.getUserId());
        house.setStatus(1);

        //Set mặc định là đang xử lý
        houseManagerService.insertHouse(house,addressID,files);


        //them toa do cua map


        return  "redirect:/admin/house-manager";
    }

    @GetMapping("/house-manager/deleteImage/{houseId}/{imageId}")
    public String deleteImage(@PathVariable Integer houseId,@PathVariable Integer imageId,Model model, HttpSession httpSession){
        System.out.println(houseId);
        System.out.println(imageId);
        houseManagerService.deleteImageById(imageId);

        return "redirect:/admin/house-manager/detail/" + houseId;
    }

    @GetMapping("/house-manager/delete/{houseid}")
    public String deleteHouse(@PathVariable Integer houseid,HttpSession httpSession){
        houseManagerService.deleteHouse(houseid);
        return "redirect:/admin/house-manager";
    }



}
