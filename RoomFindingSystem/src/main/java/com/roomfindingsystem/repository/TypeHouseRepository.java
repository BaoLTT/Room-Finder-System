package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.TypeHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeHouseRepository extends JpaRepository<TypeHouseEntity,Integer> {
    @Query(value = "SELECT * FROM room_finding_system.type_house",nativeQuery = true)
    List<TypeHouseEntity> findAll();


}
