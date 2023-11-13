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
                       @RequestParam(name = "price",required = false,defaultValue = "0") List<String> prices,
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
        for (String priceValue : prices) {
            if ("1".equals(priceValue)) {
                for (RoomDtoN room : roomService.findRoom1(0, 2000000, roomName, listType, offset, pageSize)) {
                    roomList.add(room);
                }
                totalRoom += roomService.countRoom(0, 2000000, roomName, listType);
            } else if ("2".equals(priceValue)) {
                for (RoomDtoN room : roomService.findRoom1(2000000, 4000000, roomName, listType, offset, pageSize)) {
                    roomList.add(room);
                }
                totalRoom += roomService.countRoom(2000000, 4000000, roomName, listType);
            } else if ("3".equals(priceValue)) {
                for (RoomDtoN room : roomService.findRoom1(4000000, 6000000, roomName, listType, offset, pageSize)) {
                    roomList.add(room);
                }
                totalRoom += roomService.countRoom(4000000, 6000000, roomName, listType);
            } else if ("0".equals(priceValue)) {
                roomList = (roomService.findRoom1(0, 6000000, roomName, listType, offset, pageSize));
                totalRoom = roomService.countRoom(0, 6000000, roomName, listType);
            }
        }
        totalPage = (int) Math.ceil((double) totalRoom / pageSize);

        model.addAttribute("roomName",roomName);
        model.addAttribute("currentPage",pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("rooms", roomList);
        model.addAttribute("price", prices);
        model.addAttribute("type", type);


        return"room/RoomList";

    }

}
