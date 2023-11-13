package com.roomfindingsystem.service;


import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import com.roomfindingsystem.dto.*;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface RoomService {
    RoomEntity getRoomById(int roomId);

    List<RoomImagesEntity> roomImageByRoomId(int roomId);

    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
//    RoomImagesEntity get

    List<RoomDto> getAll();

    //Homepage


    List<RoomHomeDto> viewRoomInHome();

    RoomDto findById(Integer id);

    void update(RoomDto roomDto, MultipartFile[] files) throws IOException;

    void deleteById(Integer id);

    void save(RoomDto roomDto, MultipartFile[] files) throws IOException;

    int countRoom(int min, int max, String roomName, List<Integer> type);

    //room type list in house detail
    List<RoomHouseDetailDto> viewRoomInHouse(int houseId);

    List<RoomDtoN> findRoom1(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);

    void importRooms(MultipartFile file);

    List<RoomAdminDashboardDto> getRoomStatusInAdminDashboard();

    void updateStatusDate(int roomId, int statusId);

    void deleteRoomImage(Integer imageId);
}
