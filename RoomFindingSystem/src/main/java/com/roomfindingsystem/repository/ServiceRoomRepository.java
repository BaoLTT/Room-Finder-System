package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ServiceRoomEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("serviceRoomRepository")
public interface ServiceRoomRepository extends JpaRepository<ServiceRoomEntity, Integer> {
    void deleteByRoomIdAndServiceId(Integer roomId, Integer serviceId);

    List<ServiceRoomEntity> findAllByRoomId(Integer roomId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `room_finding_system`.`service_room`\n" +
            "WHERE service_room.roomid = ?1 ;",nativeQuery = true)
    void deleteByRoomId(int roomid);
}
