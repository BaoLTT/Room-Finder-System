package com.roomfindingsystem.service;


import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import com.roomfindingsystem.dto.*;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public interface RoomService {
    RoomEntity getRoomById(int roomId);

    List<RoomImagesEntity> roomImageByRoomId(int roomId);

    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
//    RoomImagesEntity get

    List<RoomDto> getAll();

    //Homepage

    int countRoom(int houseId);
    List<RoomHomeDto> viewRoomInHome();

    List<RoomHomeDto> viewRoomNearPrice(int price);

    RoomDto findById(Integer id);

    void update(RoomDto roomDto, MultipartFile[] files) throws IOException;

    void deleteById(Integer id);

    void saveRoomAdmin(RoomDto roomDto, MultipartFile[] files) throws IOException;

    void saveRoomLandlord(RoomDto roomDto, MultipartFile[] files) throws IOException;

    int countRoom(int min1, int max1, int min2, int max2, int min3, int max3, String roomName, List<Integer> type);

    //room type list in house detail
    List<RoomHouseDetailDto> viewRoomInHouse(int houseId);

    String getRoomNameById(String id);

    List<RoomDtoN> findRoom1(int min1, int max1, int min2, int max2, int min3, int max3, String roomName, List<Integer> type, int pageIndex, int pageSize);

    void importRooms(RoomDto roomDto,MultipartFile file);

    List<RoomAdminDashboardDto> getRoomStatusInAdminDashboard();

    void updateStatusDate(int roomId, int statusId);

    void deleteRoomImage(Integer imageId);

    List<RoomDto> getRoomsInHouse(int houseId);

    //dashboard
    int countEmptyRoom();

    int countInhabitedRoom();
}
