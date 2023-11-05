package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.RoomImagesEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@SpringBootApplication
public interface RoomImageRepository extends CrudRepository<RoomImagesEntity, Long> {
    @Query("SELECT i FROM RoomImagesEntity i WHERE i.imageId = :imageId")
    RoomImagesEntity getImageByRoomId(int imageId);
}
