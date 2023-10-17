package com.roomfindingsystem.controller;


import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.vo.HouseTypeVo;
import jakarta.servlet.http.HttpSession;

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
    @GetMapping(value={"/{pageIndex}"})
    public String list(@PathVariable Integer pageIndex, @RequestParam(name = "houseName",required = false , defaultValue = "") String houseName,
                      @RequestParam(name = "price",required = false,defaultValue = "0") String price,
                       @RequestParam(name = "type", required = false,defaultValue = "1,2,3,4") List<String> type, Model model, HttpSession httpSession){
        List<Integer> listType = new ArrayList<>();
        for(String type1: type){
            listType.add(Integer.parseInt(type1));
        }
        int pageSize =4;
        int totalHouse = houseService.countHouse();
        int offset = (pageIndex -1)*pageSize;
        int totalPage = (int) Math.ceil((double) totalHouse / pageSize);
        List<HouseTypeVo>  page= houseService.findHouse1(0,6000000,houseName,listType,offset, pageSize);;
        if(price.equals("1")){
             page =houseService.findHouse1(0,2000000,houseName,listType,offset, pageSize);
        }
        if(price.equals("2")){
            page =houseService.findHouse1(2000000,4000000,houseName,listType,offset, pageSize);
        }
        if(price.equals("3")){
            page =houseService.findHouse1(4000000,6000000,houseName,listType,offset, pageSize);
        }

        model.addAttribute("houseName",houseName);
        model.addAttribute("currentPage",pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("houses", page);

        return"listing";
    
    }



}
