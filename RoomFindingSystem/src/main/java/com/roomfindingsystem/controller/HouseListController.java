package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.repository.ServiceDetailRepository;
import com.roomfindingsystem.repository.ServiceHouseRepository;
import com.roomfindingsystem.service.HouseService;

import com.roomfindingsystem.dto.HouseTypeVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.*;

@Controller
@RequestMapping("/houselist")
public class HouseListController {
    private HouseService houseService;

    public HouseListController(HouseService houseService) {
        this.houseService = houseService;
    }

    @Autowired
    private ServiceHouseRepository serviceHouseRepository;
    @Autowired
    private ServiceDetailRepository serviceDetailRepository;

//    @RequestMapping(value="", method = RequestMethod.GET)
//    public String getAll(ModelMap modelMap){
//    Iterable <House> houses = houseReponsitory.findAll();
//    modelMap.addAttribute("houses",houses);
//        return "listing";
//    }


    @GetMapping(value = {""})
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageIndex, @RequestParam(name = "houseName", required = false, defaultValue = "") String houseName,
                       @RequestParam(name = "price", required = false, defaultValue = "0") List<String> price,
                       @RequestParam(name = "type", required = false, defaultValue = "1,2,3,4") List<String> type,
                       @RequestParam(name = "service", required = false, defaultValue = "1,2,3,4,5") List<String> service, Model model, HttpSession httpSession) {
        List<Integer> listType = new ArrayList<>();
        List<Integer> listPrice = new ArrayList<>();
        for (String price1 : price) {
            listPrice.add(Integer.parseInt(price1));
        }
        List<ServiceDetailEntity> listAllService = new ArrayList<>();
        for (String type1 : type) {
            listType.add(Integer.parseInt(type1));
        }
        List<Integer> listService = new ArrayList<>();
        for (String service1 : service) {
            listService.add(Integer.parseInt(service1));
        }
        int pageSize = 4;

        int offset = (pageIndex - 1) * pageSize;
        model.addAttribute("listPrice", listPrice);
        model.addAttribute("listType", listType);
        model.addAttribute("listService", listService);

        List<HouseTypeVo> list = houseService.findHouse(0, 0, 0, 6000000, houseName, listType, listService, offset, pageSize);
        int totalHouse = houseService.countHouse(0, 0, 0, 6000000, houseName, listType, listService);

        if (listPrice.contains(1)) {
            list = houseService.findHouse(0, 0, 0, 2000000, houseName, listType, listService, offset, pageSize);
            totalHouse = houseService.countHouse(0, 0, 0, 2000000, houseName, listType, listService);
        }
        if (listPrice.contains(2)) {
            list = houseService.findHouse(0, 0, 2000000, 4000000, houseName, listType, listService, offset, pageSize);
            totalHouse = houseService.countHouse(0, 0, 2000000, 4000000, houseName, listType, listService);
        }
        if (listPrice.contains(3)) {
            list = houseService.findHouse(0, 0, 4000000, 6000000, houseName, listType, listService, offset, pageSize);
            totalHouse = houseService.countHouse(0, 0, 4000000, 6000000, houseName, listType, listService);
        }
        if (listPrice.contains(1) && listPrice.contains(3)) {
            list = houseService.findHouse(0, 2000000, 4000000, 6000000, houseName, listType, listService, offset, pageSize);
            totalHouse = houseService.countHouse(0, 2000000, 4000000, 6000000, houseName, listType, listService);
        }
        if (listPrice.contains(1) && listPrice.contains(2)) {
            list = houseService.findHouse(0, 0, 0, 4000000, houseName, listType, listService, offset, pageSize);
            totalHouse = houseService.countHouse(0, 0, 0, 6000000, houseName, listType, listService);
        }
        if (listPrice.contains(2) && listPrice.contains(3)) {
            list = houseService.findHouse(2000000, 4000000, 4000000, 6000000, houseName, listType, listService, offset, pageSize);
            totalHouse = houseService.countHouse(2000000, 4000000, 4000000, 6000000, houseName, listType, listService);
        }


        int totalPage = (int) Math.ceil((double) totalHouse / pageSize);
        model.addAttribute("houseName", houseName);

        model.addAttribute("currentPage", pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("houses", list);
        listAllService = serviceDetailRepository.getAll();
        model.addAttribute("listAllService", listAllService);
        return "houselist";
    }


}
