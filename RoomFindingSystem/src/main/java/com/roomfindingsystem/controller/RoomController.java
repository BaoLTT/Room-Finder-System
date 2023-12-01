package com.roomfindingsystem.controller;


import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
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
    public String getRoom(Model model, @PathVariable("id") int id, HttpServletRequest request){
        RoomEntity room = roomService.getRoomById(id);
        List<RoomImagesEntity> roomImagesEntities = roomService.roomImageByRoomId(id);
        model.addAttribute("room", roomService.getRoomById(id));
        model.addAttribute("roomImages", roomService.roomImageByRoomId(id));
        model.addAttribute("roomServices", roomService.getServiceByRoomId(id));
        model.addAttribute("house", houseService.getHouseByRoomId(id));
        model.addAttribute("user", userService.getUserByRoomId(id));
        model.addAttribute("request",request);
        return "room/RoomDetail";
    }

    @PostMapping("/houseDetail")
    public String getRoomInHouseDetail(Model model, @RequestParam(name = "roomId") String id){
        int id1 = Integer.parseInt(id);
        return "redirect:/room/"+id1;
    }
}
