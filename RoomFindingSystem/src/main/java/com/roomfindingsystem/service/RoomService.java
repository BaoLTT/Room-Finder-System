package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.*;
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


    List<RoomHomeDto> viewRoomInHome();

    List<RoomDtoN> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);

    int countRoom();

    //room type list in house detail
    List<RoomHouseDetailDto> viewRoomInHouse(int houseId);

    List<RoomAdminDashboardDto> getRoomStatusInAdminDashboard();

    void updateStatusDate(int roomId, int statusId);

}
