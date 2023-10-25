package com.roomfindingsystem.controller;

import com.roomfindingsystem.service.RoomService;
import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.RoomDto;
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
    public String list(@PathVariable Integer pageIndex, @RequestParam(name = "roomName",required = false , defaultValue = "") String roomName,
                       @RequestParam(name = "price",required = false,defaultValue = "0") String price,
                       @RequestParam(name = "type", required = false,defaultValue = "1,2,3,4") List<String> type, Model model, HttpSession httpSession){
        List<Integer> listType = new ArrayList<>();
        for(String type1: type){
            listType.add(Integer.parseInt(type1));
        }
        int pageSize =12;
        int totalRoom = roomService.countRoom();
        int offset = (pageIndex -1)*pageSize;
        int totalPage = (int) Math.ceil((double) totalRoom / pageSize);
        List<RoomDto>  page= roomService.findRoom1(0,6000000,roomName,listType,offset, pageSize);
        if(price.equals("1")){
            page =roomService.findRoom1(0,2000000,roomName,listType,offset, pageSize);
        }
        if(price.equals("2")){
            page =roomService.findRoom1(2000000,4000000,roomName,listType,offset, pageSize);
        }
        if(price.equals("3")){
            page =roomService.findRoom1(4000000,6000000,roomName,listType,offset, pageSize);
        }

        model.addAttribute("roomName",roomName);
        model.addAttribute("currentPage",pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("rooms", page);

        return"room/RoomList";

    }

}
