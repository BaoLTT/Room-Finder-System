package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.HouseImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseImageRepository extends JpaRepository<HouseImagesEntity,Integer> {
    @Query(value = "SELECT * FROM room_finding_system.house_images WHERE houseid=265;",nativeQuery = true)
    List<HouseImagesEntity> getImageByHouseId(int houseid);
}
