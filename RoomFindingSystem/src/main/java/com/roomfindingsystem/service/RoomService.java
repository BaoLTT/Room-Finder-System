package com.roomfindingsystem.service;


import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;



import java.util.List;


public interface RoomService {
    RoomEntity getRoomById(int roomId);
    List<RoomImagesEntity> roomImageByRoomId (int roomId);

    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
//    RoomImagesEntity get


    //Homepage

    //Homepage

    List<RoomHomeDto> viewRoomInHome();

//    void save(RoomDto roomDto);

    int countRoom();

    //room type list in house detail
    List<RoomHouseDetailDto> viewRoomInHouse(int houseId);


}
