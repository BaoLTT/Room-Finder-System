package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ServiceRoomService serviceRoomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private ServiceDetailService serviceDetailService;
    @GetMapping("/listRoom")
    public String getListRoomPage(Model model) {
        List<RoomDto> roomDtos = roomService.getAll();
        model.addAttribute("rooms", roomDtos);
        return "admin/list-room";
    }

    @GetMapping("/updateRoom/{id}")
    public String getFormUpdateRoom(@PathVariable("id") Integer id, Model model){
        RoomDto roomDto = roomService.findById(id);
        model.addAttribute("room", roomDto);
        System.out.println(roomDto);
        model.addAttribute("types", roomTypeService.findAll());
        return "admin/edit-room";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {
        List<ServiceDto> serviceDtos = new ArrayList<>();
        List<String> selects = roomDto.getServiceNames();
        System.out.println(files.length);
        if (selects != null) {
            for (String serviceName : selects) {
                ServiceDto serviceDto = new ServiceDto();
                serviceDto.setServiceName(serviceName);
                System.out.println(serviceName);
                serviceDto.setServiceId(serviceDetailService.findByName(serviceName).getServiceId());
                serviceDtos.add(serviceDto);
            }
        }
        roomDto.setServiceDtos(serviceDtos);
        roomService.update(roomDto, files);
        return "redirect:/admin/room/updateRoom/" + roomDto.getRoomId();
    }

    @GetMapping("/deleteRoom/{id}")
    public String delete(@PathVariable("id") Integer id){
        roomService.deleteById(id);
        return "redirect:/admin/room/listRoom";
    }
    @GetMapping("/insertRoom")
    public String insertRoomPage(Model model) {
        RoomDto roomDto = new RoomDto();
        model.addAttribute("room", roomDto);
        model.addAttribute("services", serviceRoomService.findAll());
        model.addAttribute("types", roomTypeService.findAll());
        return "admin/insert-room";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {
        try {
            List<ServiceDto> serviceDtos = new ArrayList<>();
            List<String> selects = roomDto.getServiceNames();
            for (String serviceName : selects) {
                ServiceDto serviceDto = new ServiceDto();
                serviceDto.setServiceName(serviceName);
                System.out.println(serviceName);
                serviceDto.setServiceId(serviceDetailService.findByName(serviceName).getServiceId());
                serviceDtos.add(serviceDto);
            }
            System.out.println(serviceDtos);
            roomDto.setServiceDtos(serviceDtos);
            roomService.save(roomDto, files);
        } catch (Exception ex) {
        }
        return "redirect:/admin/room/listRoom";
    }

    @PostMapping("/importRooms")
    public String importRoom(@RequestParam("fileExcel") MultipartFile fileExcel) {
        roomService.importRooms(fileExcel);
        return "redirect:/admin/room/listRoom";
    }
    @GetMapping("deleteImage/{roomId}/{imageId}")
    public String deleteImage(@PathVariable Integer roomId, @PathVariable Integer imageId) {
        roomService.deleteRoomImage(imageId);
        return "redirect:/updateRoom/" + roomId;
    }
}


