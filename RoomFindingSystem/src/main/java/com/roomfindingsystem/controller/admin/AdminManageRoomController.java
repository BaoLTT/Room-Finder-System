package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.entity.TypeHouseEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;
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
    @Autowired
    HouseTypeService houseTypeService;

    @GetMapping("/listRoom/{id}")
    public String getListRoomPage(@PathVariable("id") Integer id,Model model) {
        try{
            List<RoomDto> roomDtos = roomService.getRoomsInHouse(id);
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user = userService.findByEmail(email).get();
            model.addAttribute("houseId", id);
            model.addAttribute("rooms", roomDtos);
            model.addAttribute("user", user);
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console

            // Thêm thông báo lỗi cho người dùng thông qua Model
            model.addAttribute("error", "Có lỗi xảy ra.");

            // Trả về trang dashboard với thông báo lỗi
            return "404Admin";
        }

        return "admin/list-room";
    }

    @GetMapping("/updateRoom/{id}")
    public String getFormUpdateRoom(@PathVariable("id") Integer id, Model model) {
        try{
            RoomDto roomDto = roomService.findById(id);
            model.addAttribute("houseid", roomDto.getHouseId());
            model.addAttribute("room", roomDto);
            model.addAttribute("types", roomTypeService.findAll());
            model.addAttribute("listServiceNotUse",serviceDetailService.getServiceNotUse());
            model.addAttribute("listService", serviceDetailService.getServiceExceptHouseService(roomDto.getHouseId()));
            model.addAttribute("listChecked", roomDto.getServices());
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user = userService.findByEmail(email).get();
            model.addAttribute("user", user);
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console

            // Thêm thông báo lỗi cho người dùng thông qua Model
            model.addAttribute("error", "Có lỗi xảy ra.");

            // Trả về trang dashboard với thông báo lỗi
            return "404Admin";
        }
        return "admin/edit-room";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {
        try{
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
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console

            // Trả về trang dashboard với thông báo lỗi
            return "404Admin";
        }

        return "redirect:/admin/room/updateRoom/" + roomDto.getRoomId();
    }

    @GetMapping("/deleteRoom/{id}/{houseid}")
    public String delete(@PathVariable("id") Integer id,@PathVariable("houseid") Integer houseid) {
        try{
            roomService.deleteById(id);
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console
            return "404Admin";
        }

        return "redirect:/admin/room/listRoom/"+houseid;
    }

    @GetMapping("/insertRoom/{id}")
    public String insertRoomPage(@PathVariable("id") Integer id,Model model) {
        try{
            RoomDto roomDto = new RoomDto();
            model.addAttribute("room", roomDto);
            HousesEntity house = houseService.getHouseById(id);
            model.addAttribute("house", house);
            model.addAttribute("listServiceNotUse",serviceDetailService.getServiceNotUse());

            model.addAttribute("services", serviceDetailService.getServiceExceptHouseService(id));
            model.addAttribute("types", roomTypeService.findAll());
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console

            // Thêm thông báo lỗi cho người dùng thông qua Model
            model.addAttribute("error", "Có lỗi xảy ra.");

            // Trả về trang dashboard với thông báo lỗi
            return "404Admin";
        }

        return "admin/insert-room";
    }

    @PostMapping("/save/{id}")
    public String save(@PathVariable("id") Integer houseid,@ModelAttribute(name = "room") RoomDto roomDto, @RequestParam("file") MultipartFile[] files) throws IOException {
        try{
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
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console
            return "404Admin";
        }

        return "redirect:/admin/room/listRoom/"+houseid;
    }

    @PostMapping("/importRooms")
    public String importRoom(@PathVariable("houseId") Integer id,@RequestParam("fileExcel") MultipartFile fileExcel) {

        RoomDto roomDto = new RoomDto();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(email).get();
        roomDto.setCreatedBy(user.getUserId());
        roomDto.setLastModifiedBy(user.getUserId());
        roomDto.setHouseId(id);
        roomService.importRooms(roomDto,fileExcel);

        return "redirect:/admin/room/listRoom";
    }

    @GetMapping("deleteImage/{roomId}/{imageId}")
    public String deleteImage(@PathVariable Integer roomId, @PathVariable Integer imageId) {
        try{
            roomService.deleteRoomImage(imageId);
        }catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ:
            e.printStackTrace(); // In lỗi ra console
            return "404Admin";
        }

        return "redirect:/admin/room/updateRoom/" + roomId;
    }
}


