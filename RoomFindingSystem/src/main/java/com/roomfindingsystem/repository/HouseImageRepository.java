package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.HouseImagesEntity;
import jakarta.transaction.Transactional;
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
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `room_finding_system`.`house_images`\n" +
            "WHERE house_images.houseid = ?1 ;",nativeQuery = true)
    void deleteByHouseId(int houseid);


}
