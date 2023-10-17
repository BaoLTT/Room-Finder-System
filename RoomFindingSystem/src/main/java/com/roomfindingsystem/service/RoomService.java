package com.roomfindingsystem.service;


import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.vo.RoomHomeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomService {
    RoomEntity getRoomById(int roomId);
    List<RoomImagesEntity> roomImageByRoomId (int roomId);

    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
//    RoomImagesEntity get


    //Homepage
    List<RoomHomeVo> viewRoomInHome();

}
