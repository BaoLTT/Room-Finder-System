package com.roomfindersystem.repository;

import com.roomfindersystem.entity.HouseImagesEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface HouseImageRepository extends JpaRepository<HouseImagesEntity,Integer> {
    @Query(value = "SELECT * FROM room_finding_system.house_images WHERE houseid = ?1 ;",nativeQuery = true)
    List<HouseImagesEntity> getImageByHouseId(int houseid);


}
