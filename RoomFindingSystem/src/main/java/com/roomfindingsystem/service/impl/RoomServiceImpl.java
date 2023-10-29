package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.RoomHomeDto;
import com.roomfindingsystem.dto.RoomHouseDetailDto;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.reponsitory.RoomRepository;
import com.roomfindingsystem.service.RoomService;

import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.RoomDto;
import com.roomfindingsystem.vo.RoomHomeVo;
import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.RoomHomeVo;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.*;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
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
    public List<RoomHomeVo> viewRoomInHome() {
        List<RoomHomeVo> list = new ArrayList<>();
        if(!roomRepository.viewTop4Home().isEmpty()){
            if(roomRepository.viewTop4Home().size()<8) list = roomRepository.viewTop4Home();
            else for(int i = 0; i<8; i++){
                list.add(roomRepository.viewTop4Home().get(i));
            }
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
                roomHomeDto.setRoomImageLink(imageLinks);}
            roomHomeDto.setProvince(tuple.get("province_name", String.class));
            roomHomeDto.setDistrict(tuple.get("district_name", String.class));
            roomHomeDto.setWard(tuple.get("ward_name", String.class));
            roomHomeDto.setArea(tuple.get("area", Double.class));
            roomHomeDto.setPrice(tuple.get("price",Integer.class));

            roomHomeDtos.add(roomHomeDto);
        }
        return list;
        return roomHomeDtos;

    }

    @Override
    public List<RoomDto> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize) {
        List<Tuple> tuples = roomRepository.getRoomList(min, max, roomName, type, pageIndex, pageSize);
        List<RoomDto> roomDtos = new ArrayList<>();
        List<String> imageLinks ;

        for (Tuple tuple : tuples) {
            RoomDto roomDto = new RoomDto();
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
