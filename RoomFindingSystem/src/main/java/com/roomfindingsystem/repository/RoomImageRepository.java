package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.RoomImagesEntity;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface RoomImageRepository extends CrudRepository<RoomImagesEntity, Long> {
    @Query("SELECT i FROM RoomImagesEntity i WHERE i.roomId = :roomId")
    List<RoomImagesEntity> getImageByRoomId(int roomId);

    void deleteByImageId(Integer imageId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `room_finding_system`.`room_images`\n" +
            "WHERE room_images.roomid = ?1 ;",nativeQuery = true)
    void deleteByRoomId(int roomid);
}
