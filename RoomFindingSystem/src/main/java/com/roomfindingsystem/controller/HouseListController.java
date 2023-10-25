package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.vo.HouseTypeVo;
import jakarta.persistence.Index;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.*;

@Controller
@RequestMapping("/houselist")
public class HouseListController {
    private HouseService houseService;

    public HouseListController(HouseService houseService){
        this.houseService = houseService;
    }

//    @RequestMapping(value="", method = RequestMethod.GET)
//    public String getAll(ModelMap modelMap){
//    Iterable <House> houses = houseReponsitory.findAll();
//    modelMap.addAttribute("houses",houses);
//        return "listing";
//    }


    @GetMapping(value = {""})
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageIndex, @RequestParam(name = "houseName",required = false , defaultValue = "") String houseName,
                      @RequestParam(name = "price",required = false,defaultValue = "0") String price,
                       @RequestParam(name = "type", required = false,defaultValue = "1,2,3,4") List<String> type, Model model, HttpSession httpSession){
        List<Integer> listType = new ArrayList<>();
        for(String type1: type){
            listType.add(Integer.parseInt(type1));
        }
        int pageSize =4;

        int offset = (pageIndex -1)*pageSize;

        List<HouseTypeVo>  list= houseService.findHouse(0,6000000,houseName,listType,offset, pageSize);
        int totalHouse = houseService.countHouse(0, 6000000,houseName,listType);
        if(price.equals("1")){
            list =houseService.findHouse(0,2000000,houseName,listType,offset, pageSize);
            totalHouse = houseService.countHouse(0, 2000000,houseName,listType);
        }
        if(price.equals("2")){
            list =houseService.findHouse(2000000,4000000,houseName,listType,offset, pageSize);
            totalHouse = houseService.countHouse(2000000, 4000000,houseName,listType);
        }
        if(price.equals("3")){
            list =houseService.findHouse(4000000,6000000,houseName,listType,offset, pageSize);
            totalHouse = houseService.countHouse(4000000, 6000000,houseName,listType);
        }
        int totalPage = (int) Math.ceil((double) totalHouse / pageSize);
        model.addAttribute("houseName",houseName);
        model.addAttribute("currentPage",pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("houses", list);

        return"Houselist";
    }


}
