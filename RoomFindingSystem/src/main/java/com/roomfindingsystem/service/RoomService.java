package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.RoomDtoN;
import com.roomfindingsystem.dto.RoomHomeDto;
import com.roomfindingsystem.dto.RoomHouseDetailDto;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;



import java.util.List;


public interface RoomService {
    RoomEntity getRoomById(int roomId);
    List<RoomImagesEntity> roomImageByRoomId (int roomId);

    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
//    RoomImagesEntity get

    List<RoomDto> getAll();

    //Homepage


    List<RoomHomeDto> viewRoomInHome();
    RoomDto findById(Integer id);
//    void update(RoomDto roomDto);

    void deleteById(Integer id);

//    void save(RoomDto roomDto);

    int countRoom();

    //room type list in house detail
    List<RoomHouseDetailDto> viewRoomInHouse(int houseId);

    List<RoomDtoN> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);


}
