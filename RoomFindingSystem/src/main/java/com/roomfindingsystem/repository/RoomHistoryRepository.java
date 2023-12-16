package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.RoomHistoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomHistoryRepository extends JpaRepository<RoomHistoriesEntity, Integer> {
    @Query
    List<RoomHistoriesEntity> findRoomHistoriesEntitiesByRoomid(int roomId);

    @Query
    RoomHistoriesEntity findRoomHistoriesEntitiesByHistoryid(int historyId);
}
