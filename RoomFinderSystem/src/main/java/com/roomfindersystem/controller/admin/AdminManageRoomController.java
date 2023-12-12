package com.roomfindersystem.controller.admin;

import com.roomfindersystem.dto.RoomDto;
import com.roomfindersystem.dto.ServiceDto;
import com.roomfindersystem.entity.HousesEntity;
import com.roomfindersystem.entity.UserEntity;
import com.roomfindersystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/room")
@Controller
public class AdminManageRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private ServiceDetailService serviceDetailService;
    @Autowired
    UserService userService;
    @Autowired
    HouseService houseService;

    @GetMapping("/listRoom/{id}")
    public String getListRoomPage(@PathVariable("id") Integer id,Model model) {
        List<RoomDto> roomDtos = roomService.getRoomsInHouse(id);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("houseId", id);
        model.addAttribute("rooms", roomDtos);
        model.addAttribute("user", user);
        return "admin/list-room";
    }

    @GetMapping("/updateRoom/{id}")
    public String getFormUpdateRoom(@PathVariable("id") Integer id, Model model) {
        RoomDto roomDto = roomService.findById(id);
        model.addAttribute("houseid", roomDto.getHouseId());
        model.addAttribute("room", roomDto);
        model.addAttribute("types", roomTypeService.findAll());
        model.addAttribute("listService", serviceDetailService.getServiceExceptHouseService(roomDto.getHouseId()));
        model.addAttribute("listChecked", roomDto.getServices());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("user", user);
        return "admin/edit-room";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {
        List<ServiceDto> serviceDtos = new ArrayList<>();
        List<String> selects = roomDto.getServiceNames();
        if (selects != null) {
            for (String serviceName : selects) {
                ServiceDto serviceDto = new ServiceDto();
                serviceDto.setServiceName(serviceName);
                serviceDto.setServiceId(serviceDetailService.findByName(serviceName).getServiceId());
                serviceDtos.add(serviceDto);
            }
        }
        roomDto.setServiceDtos(serviceDtos);
        roomService.update(roomDto, files);
        return "redirect:/admin/room/updateRoom/" + roomDto.getRoomId();
    }

    @GetMapping("/deleteRoom/{id}/{houseid}")
    public String delete(@PathVariable("id") Integer id,@PathVariable("houseid") Integer houseid) {
        roomService.deleteById(id);
        return "redirect:/admin/room/listRoom/"+houseid;
    }

    @GetMapping("/insertRoom/{id}")
    public String insertRoomPage(@PathVariable("id") Integer id,Model model) {
        RoomDto roomDto = new RoomDto();
        model.addAttribute("room", roomDto);
        HousesEntity house = houseService.getHouseById(id);
        model.addAttribute("house", house);
        model.addAttribute("services", serviceDetailService.getServiceExceptHouseService(id));
        model.addAttribute("types", roomTypeService.findAll());
        return "admin/insert-room";
    }

    @PostMapping("/save/{id}")
    public String save(@PathVariable("id") Integer houseid,@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {
        List<ServiceDto> serviceDtos = new ArrayList<>();
        List<String> selects = roomDto.getServiceNames();
        if (selects != null) {
            for (String serviceName : selects) {
                ServiceDto serviceDto = new ServiceDto();
                serviceDto.setServiceName(serviceName);
                serviceDto.setServiceId(serviceDetailService.findByName(serviceName).getServiceId());
                serviceDtos.add(serviceDto);
            }
        }
        roomDto.setServiceDtos(serviceDtos);
        roomDto.setHouseId(houseid);
        System.out.println(roomDto.getHouseId());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        roomDto.setCreatedBy(user.getUserId());
        roomDto.setLastModifiedBy(user.getUserId());

        roomService.saveRoomAdmin(roomDto, files);
        return "redirect:/admin/room/listRoom/"+houseid;
    }

    @PostMapping("/importRooms")
    public String importRoom(@RequestParam("fileExcel") MultipartFile fileExcel) {
        RoomDto roomDto = new RoomDto();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        roomDto.setCreatedBy(user.getUserId());
        roomDto.setLastModifiedBy(user.getUserId());
        roomService.importRooms(roomDto,fileExcel);

        return "redirect:/admin/room/listRoom";
    }

    @GetMapping("deleteImage/{roomId}/{imageId}")
    public String deleteImage(@PathVariable Integer roomId, @PathVariable Integer imageId) {
        roomService.deleteRoomImage(imageId);
        return "redirect:/admin/room/updateRoom/" + roomId;
    }
}


