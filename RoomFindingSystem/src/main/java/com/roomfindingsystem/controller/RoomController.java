package com.roomfindingsystem.controller;


import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.service.*;
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
    @Autowired
    private ServiceRoomService serviceRoomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private ServiceDetailService serviceDetailService;

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

    @PostMapping("/houseDetail")
    public String getRoomInHouseDetail(Model model, @RequestParam(name = "roomId") String id){
        int id1 = Integer.parseInt(id);
        return "redirect:/room/"+id1;
    }

    @GetMapping("/listRoomPage")
    public String getListRoomPage(Model model) {
        List<RoomDto> roomDtos = roomService.getAll();
        model.addAttribute("rooms", roomDtos);
        return "/list-room";
    }

    @GetMapping("/updateRoom/{id}")
    public String getFormUpdateRoom(@PathVariable("id") Integer id, Model model){
        RoomDto roomDto = roomService.findById(id);
        model.addAttribute("room", roomDto);
        System.out.println(roomDto);
        model.addAttribute("types", roomTypeService.findAll());
        return "edit-room";
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
        return "redirect:/room/updateRoom/" + roomDto.getRoomId();
    }

    @GetMapping("/deleteRoom/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        roomService.deleteById(id);
        return "redirect:/room/listRoomPage";
    }
    @GetMapping("/insertRoomPage")
    public String insertRoomPage(Model model) {
        RoomDto roomDto = new RoomDto();
        model.addAttribute("room", roomDto);
        model.addAttribute("services", serviceRoomService.findAll());
        model.addAttribute("types", roomTypeService.findAll());
        return "insert-room";
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
        return "redirect:/room/listRoomPage";
    }

    @PostMapping("/importRooms")
    public String importRoom(@RequestParam("fileExcel") MultipartFile fileExcel) {
        roomService.importRooms(fileExcel);
        return "redirect:/room/listRoomPage";
    }
    @GetMapping("deleteImage/{roomId}/{imageId}")
    public String deleteImage(@PathVariable Integer roomId, @PathVariable Integer imageId) {
        roomService.deleteRoomImage(imageId);
        return "redirect:/room/updateRoom/" + roomId;
    }
}
