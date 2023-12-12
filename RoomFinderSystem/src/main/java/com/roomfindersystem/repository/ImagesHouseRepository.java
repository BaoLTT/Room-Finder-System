package com.roomfindersystem.repository;

import com.roomfindersystem.entity.HouseImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesHouseRepository extends JpaRepository<HouseImagesEntity,Integer> {

}
