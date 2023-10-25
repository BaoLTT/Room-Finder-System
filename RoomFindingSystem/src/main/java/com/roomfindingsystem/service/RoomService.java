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



    //Homepage
    List<RoomHomeVo> viewRoomInHome();

    List<RoomDto> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);

    int countRoom();


}
