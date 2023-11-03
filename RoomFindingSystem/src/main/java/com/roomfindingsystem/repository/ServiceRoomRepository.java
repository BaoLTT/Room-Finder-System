package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ServiceRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("serviceRoomRepository")
public interface ServiceRoomRepository extends JpaRepository<ServiceRoomEntity, Integer> {
    void deleteByRoomIdAndServiceId(Integer roomId, Integer serviceId);

    List<ServiceRoomEntity> findAllByRoomId(Integer roomId);
}
