package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.RoomDtoN;
import com.roomfindingsystem.service.RoomService;

import com.roomfindingsystem.dto.RoomDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/roomlist")
public class RoomListController {
    @Autowired
    private RoomService roomService;
    @GetMapping(value={""})
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageIndex,
                       @RequestParam(name = "roomName",required = false , defaultValue = "") String roomName,
                       @RequestParam(name = "price", required = false, defaultValue = "") List<String> price,
                       @RequestParam(name = "floor", required = false,defaultValue = "") List<String> floor,
                       @RequestParam(name = "type", required = false,defaultValue = "") List<String> type, HttpServletRequest request, Model model){
        List<Integer> listType = new ArrayList<>();
        List<Integer> listFloor = new ArrayList<>();
        List<String> listAllFloor = roomService.findAllDistinctFloors();
        List<Integer> listPrice = new ArrayList<>();
        if(type.size()==0){
            listType.add(1);
            listType.add(2);
            listType.add(3);
        }else {
            for(String type1: type){
                listType.add(Integer.parseInt(type1));
            }
        }

        if(floor.size()==0){
            for(String floorItem: listAllFloor){
                listFloor.add(Integer.parseInt(floorItem));
            }
        }else {
            for(String floor1: floor){
                listFloor.add(Integer.parseInt(floor1));
            }
        }

        int pageSize =12;
        int totalRoom = 0;
        int offset = (pageIndex -1)*pageSize;
        int totalPage;
        List<RoomDtoN> roomList = new ArrayList<>();
        int min1=0, max1=0, min2 =0, max2=0, min3=0,  max3=0;
        if(price.size()==0){
            max1 = 1999999;
            min2 = 2000000;
            max2 = 4000000;
            min3 = 4000000;
            max3 = 9000000;
        }else {
            if(price.contains("1")){
                max1 = 1999999;
            }
            if(price.contains("2")){
                min2 = 2000000;
                max2 = 4000000;
            }
            if(price.contains("3")){
                min3 = 4000001;
                max3 = 9000000;
            }
        }


        roomList = (roomService.findRoom1(min1, max1, min2, max2, min3, max3, roomName, listType, offset, pageSize, listFloor));
        totalRoom = roomService.countRoom(min1, max1, min2, max2, min3, max3, roomName, listType, listFloor);
        if(totalRoom<=pageSize){
            totalPage=0;
        }else{
            totalPage = (int) Math.ceil((double) totalRoom / pageSize);
        }


        model.addAttribute("roomName",roomName);
        model.addAttribute("currentPage",pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("rooms", roomList);
        model.addAttribute("price", price);
        model.addAttribute("type", type);
        model.addAttribute("request",request);
        model.addAttribute("listAllFloor",listAllFloor);
        model.addAttribute("floor",floor);


        return"room/RoomList";

    }

}
