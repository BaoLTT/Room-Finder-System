package com.roomfindingsystem.repository;


import com.roomfindingsystem.entity.TypeHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("houseTypeRepository")
public interface HouseTypeRepository extends JpaRepository<TypeHouseEntity, Integer> {
    List<TypeHouseEntity> findAll();
}
