package com.roomfindersystem.repository;

import com.roomfindersystem.entity.RoomImagesEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
}
