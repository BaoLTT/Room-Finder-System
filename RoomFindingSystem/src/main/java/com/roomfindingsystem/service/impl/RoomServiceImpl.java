package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import com.roomfindingsystem.entity.ServiceRoomEntity;
import com.roomfindingsystem.repository.RoomRepository;
import com.roomfindingsystem.repository.RoomTypeRepository;
import com.roomfindingsystem.repository.ServiceDetailRepository;
import com.roomfindingsystem.repository.ServiceRoomRepository;
import com.roomfindingsystem.service.RoomService;


import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import java.util.*;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ModelMapper modelMapper;
    private final ServiceRoomRepository serviceRoomRepository;
    private final ServiceDetailRepository serviceDetailRepository;

    @Override
    public RoomEntity getRoomById(int roomId) {
        return roomRepository.getRoomById(roomId);

    }

    @Override
    public List<RoomImagesEntity> roomImageByRoomId(int roomId) {

        return roomRepository.getImageByRoomId(roomId);

    }

    @Override
    public List<ServiceDetailEntity> getServiceByRoomId(int roomId) {
        return roomRepository.getServiceByRoomId(roomId);
    }

    @Override
    public List<RoomDto> getAll() {
        List<RoomEntity> roomEntities = roomRepository.findAllRooms();

        List<RoomDto> roomDtos = roomEntities.stream().map(roomEntity -> {
            RoomDto roomDto = modelMapper.map(roomEntity, RoomDto.class);
            roomDto.setTypeName(roomTypeRepository.findById(roomEntity.getRoomType()).get().getTypeName());
            if (roomEntity.getStatusid() == 1) {
                roomDto.setStatus("ACTIVE");
            } else {
                roomDto.setStatus("INACTIVE");
            }
            List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());

            StringBuilder servicesBuilder = new StringBuilder();

            for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
                if (!servicesBuilder.isEmpty()) {
                    servicesBuilder.append(", ");
                }
                servicesBuilder.append(serviceDetailEntity.getServiceName());
            }
            roomDto.setServices(servicesBuilder.toString());

            return roomDto;
        }).toList();
        return roomDtos;
    }

    @Override
    public List<RoomHomeDto> viewRoomInHome() {
        List<Tuple> tuples = roomRepository.viewRoomInHome();
        List<RoomHomeDto> roomHomeDtos = new ArrayList<>();
        List<String> imageLinks ;

        for (Tuple tuple : tuples) {
            RoomHomeDto roomHomeDto = new RoomHomeDto();
            roomHomeDto.setRoomId(tuple.get("RoomID", Integer.class));
            roomHomeDto.setRoomName(tuple.get("Room_Name", String.class));
            roomHomeDto.setHouseName(tuple.get("House_Name", String.class));
            roomHomeDto.setRoomType(tuple.get("Type_Name", String.class));
            roomHomeDto.setAddressDetail(tuple.get("Address_Details", String.class));
            String imageLink = (tuple.get("Image_Link", String.class));
            if(imageLink == null)
            {roomHomeDto.setRoomImageLink(null);}
            else {imageLinks = Arrays.asList(imageLink.split(","));
                roomHomeDto.setRoomImageLink(imageLinks.get(0));}
            roomHomeDto.setProvince(tuple.get("province_name", String.class));
            roomHomeDto.setDistrict(tuple.get("district_name", String.class));
            roomHomeDto.setWard(tuple.get("ward_name", String.class));
            roomHomeDto.setArea(tuple.get("area", Double.class));
            roomHomeDto.setPrice(tuple.get("price",Integer.class));

            roomHomeDtos.add(roomHomeDto);
        }


        return roomHomeDtos;

    }

    @Override

    public RoomDto findById(Integer id) {
        Optional<RoomEntity> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            return null;
        }
        RoomEntity roomEntity = room.get();
        RoomDto roomDto = modelMapper.map(roomEntity, RoomDto.class);
        roomDto.setTypeName(roomTypeRepository.findById(roomEntity.getRoomType()).get().getTypeName());
        roomDto.setTypeId(roomEntity.getRoomType());
        if (roomEntity.getStatusid() == 1) {
            roomDto.setStatus("ACTIVE");
        } else {
            roomDto.setStatus("INACTIVE");
        }
        List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());

        StringBuilder servicesBuilder = new StringBuilder();
        List<String> serviceNames = new ArrayList<>();

        for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
            if (!servicesBuilder.isEmpty()) {
                servicesBuilder.append(", ");
            }
            servicesBuilder.append(serviceDetailEntity.getServiceName());
            serviceNames.add(serviceDetailEntity.getServiceName());
        }
        roomDto.setServices(servicesBuilder.toString());
        List<ServiceDto> serviceDtos = serviceDetailEntities.stream().map(serviceDetailEntity -> {
            return modelMapper.map(serviceDetailEntity, ServiceDto.class);
        }).toList();
        roomDto.setServiceDtos(serviceDtos);
        roomDto.setServiceNames(serviceNames);
        return roomDto;
    }



//    @Override
//    public void update(RoomDto roomDto) {
//        RoomEntity room = roomRepository.findById(roomDto.getRoomId()).get();
//
//        RoomEntity saveRoom = new RoomEntity();
//
//        saveRoom.setRoomId(room.getRoomId());
//        saveRoom.setArea(roomDto.getArea());
//        saveRoom.setCreatedDate(room.getCreatedDate());
//        saveRoom.setCreatedBy(room.getCreatedBy());
//        saveRoom.setDescription(roomDto.getDescription());
//        saveRoom.setHouseId(room.getHouseId());
//        saveRoom.setLastModifiedBy(room.getLastModifiedBy());
//        saveRoom.setLastModifiedDate(LocalDate.now());
//        saveRoom.setPrice(roomDto.getPrice());
//        saveRoom.setRoomName(roomDto.getRoomName());
//        saveRoom.setRoomType(room.getRoomType());
//        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
//            saveRoom.setStatusId(1);
//        } else {
//            saveRoom.setStatusId(0);
//        }
//        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(roomDto.getRoomId());
//        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
//            serviceRoomRepository.deleteByRoomIdAndServiceId(roomDto.getRoomId(), serviceRoomEntity.getServiceId());
//        }
//        for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
//            ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
//            serviceRoomEntity.setServiceId(serviceDto.getServiceId());
//            serviceRoomEntity.setRoomId(saveRoom.getRoomId());
//            serviceRoomRepository.save(serviceRoomEntity);
//        }
//        roomRepository.save(saveRoom);
//    }

    @Override
    public void deleteById(Integer id) {
        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(Math.toIntExact(id));
        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
            serviceRoomRepository.deleteByRoomIdAndServiceId(Math.toIntExact(id), serviceRoomEntity.getServiceId());
        }
        roomRepository.deleteById(id);
    }


    @Override
    public int countRoom() {
        return roomRepository.countRoom();
    }


    @Override
    public List<RoomHouseDetailDto> viewRoomInHouse(int houseId) {
        List<Tuple> tuples = roomRepository.viewRoomInHouseDetail(houseId);
        List<RoomHouseDetailDto> roomDtos = new ArrayList<>();
        List<String> roomList ;
        Set<String> uniquePairs = new HashSet<>();

        for (Tuple tuple : tuples) {
//            int houseId = tuple.get("HouseID", Integer.class);
            int typeId = tuple.get("TypeID", Integer.class);
            String pair = houseId + "-" + typeId;

            // Kiểm tra xem cặp (HouseID, TypeID) đã xuất hiện chưa
            if (!uniquePairs.contains(pair)) {
                RoomHouseDetailDto roomDto = new RoomHouseDetailDto();
                roomDto.setRoomId(tuple.get("RoomID", Integer.class));
                roomDto.setTypeId(typeId);
                roomDto.setTypeName(tuple.get("type_name", String.class));
                roomDto.setHouseId(houseId);
                roomDto.setHouseName(tuple.get("house_name", String.class));
                roomDto.setPrice(tuple.get("price", Integer.class));

                String roomName = tuple.get("room_list", String.class);
                if (roomName == null) {
                    roomDto.setRoomList(null);
                } else {
                    roomList = Arrays.asList(roomName.split(","));
                    roomDto.setRoomList(roomList);
                }

                roomDtos.add(roomDto);

                // Đánh dấu cặp (HouseID, TypeID) đã xuất hiện
                uniquePairs.add(pair);
            }
        }

        return roomDtos;
    }

    @Override

    public List<RoomDtoN> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize) {
        List<Tuple> tuples = roomRepository.getRoomList(min, max, roomName, type, pageIndex, pageSize);
        List<RoomDtoN> roomDtos = new ArrayList<>();
        List<String> imageLinks ;
       for (Tuple tuple : tuples) {
            RoomDtoN roomDto = new RoomDtoN();
            roomDto.setRoomId(tuple.get("roomid", Integer.class));
            roomDto.setRoomName(tuple.get("room_name", String.class));
            roomDto.setHouseName(tuple.get("house_name", String.class));
            roomDto.setPrice(tuple.get("price", Integer.class));
            roomDto.setRoomType(tuple.get("type_name", String.class));
            String imageLink = (tuple.get("images", String.class));
            if(imageLink == null)
            {roomDto.setRoomImages(null);}
            else {imageLinks = Arrays.asList(imageLink.split(","));
                roomDto.setRoomImages(imageLinks);}


            roomDtos.add(roomDto);
        }
        return roomDtos;
    }

    public List<RoomAdminDashboardDto> getRoomStatusInAdminDashboard() {
        List<Tuple> tuples = roomRepository.getRoomStatusInAdminDashboard();
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        for(Tuple tuple: tuples){
            RoomAdminDashboardDto roomAdminDashboardDto = new RoomAdminDashboardDto();
            roomAdminDashboardDto.setRoomId(tuple.get("RoomId", Integer.class));
            roomAdminDashboardDto.setHouseName(tuple.get("House_Name", String.class));
            roomAdminDashboardDto.setRoomName(tuple.get("Room_Name", String.class));
            roomAdminDashboardDto.setFullName(tuple.get("full_Name", String.class));
            int statusIDint = tuple.get("statusId", Integer.class);
            if(statusIDint == 1){
                roomAdminDashboardDto.setStatus("Còn trống");
            } else if(statusIDint == 2){
                roomAdminDashboardDto.setStatus("Đã có người ở");
            } else roomAdminDashboardDto.setStatus("Tìm người ở ghép");
            java.sql.Date sqlDate = (java.sql.Date) tuple.get("status_update_date", Date.class);
            if(sqlDate == null)
            {roomAdminDashboardDto.setStatusUpdateDate(null);}
            else {
                LocalDate localDate = sqlDate.toLocalDate();
                roomAdminDashboardDto.setStatusUpdateDate(localDate);
            }
            list.add(roomAdminDashboardDto);
        }
        list.sort((room1, room2) -> {
            LocalDate currentDate = LocalDate.now();
            LocalDate date1 = room1.getStatusUpdateDate() != null ? room1.getStatusUpdateDate() : currentDate;
            LocalDate date2 = room2.getStatusUpdateDate() != null ? room2.getStatusUpdateDate() : currentDate;
            return currentDate.compareTo(date1) - currentDate.compareTo(date2);
        });
        return list;
    }

    @Transactional
    public void updateStatusDate(int roomId, int statusId) {
        RoomEntity room = roomRepository.findById(roomId).orElse(null); // Thay YourEntity và yourRepository bằng entity và repository thực tế của bạn
        if (room != null) {
            room.setStatusUpdateDate(LocalDate.now());
            room.setStatusId(statusId);
            roomRepository.save(room);
        }
    }


       
}
