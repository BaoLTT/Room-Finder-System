package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.reponsitory.RoomRepository;
import com.roomfindingsystem.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomEntityRepository;
    @Override
    public RoomEntity getRoomById(int roomId) {
        return roomEntityRepository.getRoomById(roomId);
    }

    @Override
    public List<RoomImagesEntity> roomImageByRoomId(int roomId) {
        return roomEntityRepository.getImageByRoomId(roomId);
    }

    @Override
    public List<ServiceDetailEntity> getServiceByRoomId(int roomId) {
        return roomEntityRepository.getServiceByRoomId(roomId);
    }


}
