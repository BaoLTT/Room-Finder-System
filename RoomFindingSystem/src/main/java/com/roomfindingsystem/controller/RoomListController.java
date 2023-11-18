package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.RoomDtoN;
import com.roomfindingsystem.service.RoomService;

import com.roomfindingsystem.dto.RoomDto;
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
@RequestMapping("/RoomList")
public class RoomListController {
    @Autowired
    private RoomService roomService;
    @GetMapping(value={"/{pageIndex}"})
    public String list(@PathVariable Integer pageIndex,
                       @RequestParam(name = "roomName",required = false , defaultValue = "") String roomName,
                       @RequestParam(name = "minPrice",required = false, defaultValue = "0") String minPrice,
                       @RequestParam(name = "maxPrice",required = false, defaultValue = "10") String maxPrice,
                       @RequestParam(name = "type", required = false,defaultValue = "1, 2, 3") List<String> type, Model model){
        List<Integer> listType = new ArrayList<>();
        for(String type1: type){
            listType.add(Integer.parseInt(type1));
        }
        int pageSize =12;
        int totalRoom = 0;
        int offset = (pageIndex -1)*pageSize;
        int totalPage;
        List<RoomDtoN> roomList = new ArrayList<>();
        int min = Integer.parseInt(minPrice);
        int max = Integer.parseInt(maxPrice);

        roomList = (roomService.findRoom1(min*1000000, max*1000000, roomName, listType, offset, pageSize));
        totalRoom = roomService.countRoom(min*1000000, max*1000000, roomName, listType);
        if(totalRoom<=pageSize){
            totalPage=0;
        }else{
            totalPage = (int) Math.ceil((double) totalRoom / pageSize);
        }

        model.addAttribute("roomName",roomName);
        model.addAttribute("currentPage",pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("rooms", roomList);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("type", type);


        return"room/RoomList";

    }

}
