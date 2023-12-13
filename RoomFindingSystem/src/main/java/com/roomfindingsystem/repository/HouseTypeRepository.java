package com.roomfindingsystem.repository;


import com.roomfindingsystem.entity.TypeHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("houseTypeRepository")
public interface HouseTypeRepository extends JpaRepository<TypeHouseEntity, Integer> {
    List<TypeHouseEntity> findAll();

    @Query(value = "SELECT th.typeid, th.type_name, th.created_date\n" +
            "FROM type_house th\n" +
            "LEFT JOIN houses h ON th.typeid = h.type_houseid\n" +
            "WHERE h.type_houseid IS NULL ",nativeQuery = true)
    List<TypeHouseEntity> findTypeNotUse();
}
