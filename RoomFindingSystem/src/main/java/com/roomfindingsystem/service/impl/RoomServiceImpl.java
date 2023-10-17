package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.reponsitory.RoomRepository;
import com.roomfindingsystem.service.RoomService;
import com.roomfindingsystem.vo.RoomHomeVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;
    @Override
    public RoomEntity getRoomById(int roomId) {
        return roomRepository.getRoomById(roomId);
    }

    @Override
    public List<RoomImagesEntity> roomImageByRoomId(int roomId) {
        return roomRepository.getImageByRoomId(roomId);
    }

    @Override
    public List<ServiceDetailEntity> getServiceByRoomId(int roomId) {
        return roomRepository.getServiceByRoomId(roomId);
    }

    @Override
    public List<RoomHomeVo> viewRoomInHome() {
        List<RoomHomeVo> list = new ArrayList<>();
        if(!roomRepository.viewTop4Home().isEmpty()){
            if(roomRepository.viewTop4Home().size()<8) list = roomRepository.viewTop4Home();
            else for(int i = 0; i<8; i++){
                list.add(roomRepository.viewTop4Home().get(i));
            }
        }
        return list;
    }


}
