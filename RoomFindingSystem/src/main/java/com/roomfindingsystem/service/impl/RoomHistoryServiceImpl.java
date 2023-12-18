package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomHistoriesEntity;
import com.roomfindingsystem.repository.RoomHistoryRepository;
import com.roomfindingsystem.repository.RoomRepository;
import com.roomfindingsystem.service.RoomHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RoomHistoryServiceImpl implements RoomHistoryService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomHistoryRepository roomHistoryRepository;

    @Override
    public void updateRoomStatus(int roomId, int newStatus) {
        RoomEntity room = roomRepository.getRoomById(roomId);
        int currentStatus = room.getStatusid();

        if (currentStatus != newStatus) {
            ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
            LocalDate currentTime = LocalDate.now(zoneId);
            // Cập nhật thông tin trong bảng lịch sử
            RoomHistoriesEntity roomHistory = new RoomHistoriesEntity(roomId, newStatus, currentTime);
            roomHistoryRepository.save(roomHistory);


        }

    }

    @Override
    public void addRoomHistory(String roomName, int houseId) {
        RoomEntity room = roomRepository.findRoomEntityByRoomNameAndHouseId(roomName, houseId);
        // Chọn múi giờ Việt Nam
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate currentTime = LocalDate.now(zoneId);
        RoomHistoriesEntity roomHistory = new RoomHistoriesEntity(room.getRoomid(), room.getStatusid(), currentTime);
        roomHistoryRepository.save(roomHistory);

    }

    @Override
    public long countEmptyRoomDay(int roomId) {
        List<RoomHistoriesEntity> list = roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId);
        long days = 0;
        if (list.isEmpty()) return 0;
        if (list.get(0).getStatus() == 1) {
            for (int i = 0; i < list.size() - 1; i = i + 2) {
                days += ChronoUnit.DAYS.between(list.get(i).getStartDate(), list.get(i + 1).getStartDate());
            }

        } else {
            for (int i = 1; i < list.size() - 1; i = i + 2) {
                days += ChronoUnit.DAYS.between(list.get(i).getStartDate(), list.get(i + 1).getStartDate());
            }

        }
        return days;
    }

    @Override
    public long countRoomDay(int roomId) {
        List<RoomHistoriesEntity> list = roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId);
        long days = 0;
        if (list.isEmpty()) return 0;
        for (int i = 0; i < list.size()-1; i++) {
            days += ChronoUnit.DAYS.between(list.get(i).getStartDate(), list.get(i + 1).getStartDate());

        }
        return days;
    }
}

