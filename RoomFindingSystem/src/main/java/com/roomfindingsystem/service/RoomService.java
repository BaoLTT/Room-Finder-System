package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.RoomHomeDto;
import com.roomfindingsystem.dto.RoomHouseDetailDto;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.RoomDto;
import com.roomfindingsystem.vo.RoomHomeVo;

import org.springframework.stereotype.Repository;
import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.RoomHomeVo;


import java.util.List;


public interface RoomService {
    RoomEntity getRoomById(int roomId);
    List<RoomImagesEntity> roomImageByRoomId (int roomId);

    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
//    RoomImagesEntity get



    //Homepage
    List<RoomHomeVo> viewRoomInHome();

    List<RoomHomeDto> viewRoomInHome();

    List<RoomDto> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);

    int countRoom();

    //room type list in house detail
    List<RoomHouseDetailDto> viewRoomInHouse(int houseId);


}
