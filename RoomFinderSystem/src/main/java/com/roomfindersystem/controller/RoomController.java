package com.roomfindersystem.controller;


import com.roomfindersystem.dto.HouseImageLink;
import com.roomfindersystem.dto.RoomDto;
import com.roomfindersystem.dto.ServiceDto;
import com.roomfindersystem.entity.*;
import com.roomfindersystem.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("")
@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;

    @GetMapping("/house/{houseName}/room/{id}")
    public String getRoom(Model model, @PathVariable("id") int id, HttpServletRequest request){
        RoomEntity room = roomService.getRoomById(id);
        List<RoomImagesEntity> roomImagesEntities = roomService.roomImageByRoomId(id);
        List<ServiceDetailEntity> roomServices = roomService.getServiceByRoomId(id);
        HousesEntity house = houseService.getHouseByRoomId(id);
        UserEntity user = userService.getUserByRoomId(id);
        List <HouseImageLink> houseImages = houseService.getImageById(house.getHouseId());


        model.addAttribute("room", room);
        model.addAttribute("roomImages", roomImagesEntities);
        model.addAttribute("houseImages", houseImages);
        model.addAttribute("roomServices", roomServices);
        model.addAttribute("house", house);
        model.addAttribute("user", user);
        model.addAttribute("request",request);
        model.addAttribute("rooms", roomService.viewRoomNearPrice(room.getPrice()));
        return "room/RoomDetail";
    }

    @PostMapping("/houseDetail")
    public String getRoomInHouseDetail(Model model, @RequestParam(name = "roomId") String id,@RequestParam(name = "houseName") String houseName){
        int id1 = Integer.parseInt(id);
        System.out.println(houseName);
        return "redirect:/house/"+houseName+"/room/"+id1;
    }
}
