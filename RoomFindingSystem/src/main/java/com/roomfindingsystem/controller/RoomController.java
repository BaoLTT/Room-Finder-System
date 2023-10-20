package com.roomfindingsystem.controller;


import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/room")
@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getRoom(Model model, @PathVariable("id") int id){
        RoomEntity room = roomService.getRoomById(id);
        List<RoomImagesEntity> roomImagesEntities = roomService.roomImageByRoomId(id);
        model.addAttribute("room", roomService.getRoomById(id));
        model.addAttribute("roomImages", roomService.roomImageByRoomId(id));
        model.addAttribute("roomServices", roomService.getServiceByRoomId(id));
        model.addAttribute("house", houseService.getHouseByRoomId(id));
        model.addAttribute("user", userService.getUserByRoomId(id));
        return "room/RoomDetail";
    }
}
