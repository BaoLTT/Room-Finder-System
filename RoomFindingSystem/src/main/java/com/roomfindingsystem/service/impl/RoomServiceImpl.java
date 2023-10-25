package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.reponsitory.RoomRepository;
import com.roomfindingsystem.service.RoomService;

import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.RoomDto;
import com.roomfindingsystem.vo.RoomHomeVo;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

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
        }
        return list;

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


}
