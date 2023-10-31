package com.roomfindingsystem.service;


import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.RoomDto;
import com.roomfindingsystem.vo.RoomHomeVo;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface RoomService {
    RoomEntity getRoomById(int roomId);
    List<RoomImagesEntity> roomImageByRoomId (int roomId);

    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
//    RoomImagesEntity get

    List<RoomDto> getAll();

    //Homepage
    List<RoomHomeVo> viewRoomInHome();
    RoomDto findById(Integer id);
    void update(RoomDto roomDto);

    void deleteById(Integer id);

    void save(RoomDto roomDto);

    int countRoom();

    List<RoomDto> findRoom1(int i, int i1, String roomName, List<Integer> listType, int offset, int pageSize);
}
