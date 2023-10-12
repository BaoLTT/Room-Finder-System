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
    @GetMapping ("/view")
    public String viewHomepage(final Model model, HttpSession httpSession){
        System.out.println(houseService.getAllHouse().toString());
        model.addAttribute("houses", houseService.getAllHouse());
        return "listing";
    }

    @GetMapping(value={"/{pageIndex}"})
    public String list(@PathVariable Integer pageIndex, @RequestParam(name = "houseName",required = false , defaultValue = "") String houseName,
                      @RequestParam(name = "price",required = false,defaultValue = "0") String price,
                       @RequestParam(name = "type", required = false,defaultValue = "1,2,3,4") List<String> type, Model model, HttpSession httpSession){
        List<Integer> listType = new ArrayList<>();
        for(String type1: type){
            listType.add(Integer.parseInt(type1));
        }

        Page<HouseTypeVo> page = houseService.findHouse(0,6000000,houseName,listType,pageIndex, 4);;
        if(price.equals("1")){
             page =houseService.findHouse(0,2000000,houseName,listType,pageIndex, 4);
        }
        if(price.equals("2")){
            page =houseService.findHouse(2000000,4000000,houseName,listType,pageIndex, 4);
        }
        if(price.equals("3")){
            page =houseService.findHouse(4000000,6000000,houseName,listType,pageIndex, 4);
        }




        model.addAttribute("house1", houseService.getAllHouse());
        model.addAttribute("houseName",houseName);
        model.addAttribute("currentPage",pageIndex);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("houses", page.getContent());

        return"listing";
    }


}
