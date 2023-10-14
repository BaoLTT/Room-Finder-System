package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SpringBootApplication
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    @Query("SELECT r FROM RoomEntity r WHERE r.roomId = :roomId")
    RoomEntity getRoomById(int roomId);

    @Query("SELECT i FROM RoomImagesEntity i WHERE i.roomId = :roomId")
    List<RoomImagesEntity> getImageByRoomId(int roomId);

    @Query("SELECT s\n" +
            "FROM ServiceDetailEntity s\n" +
            "JOIN ServiceRoomEntity rs ON s.serviceId = rs.serviceId\n" +
            "WHERE rs.roomId = :roomId")
    List<ServiceDetailEntity> getServiceByRoomId(int roomId);
}
