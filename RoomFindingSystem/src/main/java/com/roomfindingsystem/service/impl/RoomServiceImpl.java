package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.service.RoomService;

import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

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

        }
        roomRepository.deleteById(id);
        return roomDtos;
    }

//    @Override
//    public void save(RoomDto roomDto) {
//        RoomEntity saveRoom = new RoomEntity();
//        saveRoom.setArea(roomDto.getArea());
//        saveRoom.setCreatedDate(LocalDate.now());
//        saveRoom.setCreatedBy(1);
//        saveRoom.setDescription(roomDto.getDescription());
//        saveRoom.setHouseId(1);
//        saveRoom.setLastModifiedBy(1);
//        saveRoom.setLastModifiedDate(LocalDate.now());
//        saveRoom.setPrice(roomDto.getPrice());
//        saveRoom.setRoomName(roomDto.getRoomName());
//        saveRoom.setRoomType(roomDto.getTypeId());
//        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
//            saveRoom.setStatusId(1);
//        } else {
//            saveRoom.setStatusId(0);
//        }
//
//        roomRepository.save(saveRoom);
//
//        for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
//            ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
//            serviceRoomEntity.setServiceId(serviceDto.getServiceId());
//            serviceRoomEntity.setRoomId(saveRoom.getRoomId());
//            serviceRoomRepository.save(serviceRoomEntity);
//        }
//    }

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
}
