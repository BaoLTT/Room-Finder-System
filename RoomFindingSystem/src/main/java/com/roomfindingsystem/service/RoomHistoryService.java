package com.roomfindingsystem.service;

public interface RoomHistoryService {
    void updateRoomStatus(int roomId, int newStatus);

    void addRoomHistory(String roomName,int houseId);

    long countEmptyRoomDay(int roomId);

    long countRoomDay(int roomId);
}
