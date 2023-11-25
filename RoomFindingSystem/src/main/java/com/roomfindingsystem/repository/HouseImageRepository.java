package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.HouseImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseImageRepository extends JpaRepository<HouseImagesEntity,Integer> {
}
