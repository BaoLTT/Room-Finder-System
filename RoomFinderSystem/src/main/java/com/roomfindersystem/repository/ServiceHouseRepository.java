package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.entity.ServiceHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("serviceRepository")
public interface ServiceHouseRepository extends JpaRepository<ServiceHouseEntity, Integer> {
    @Modifying
    @Query(value = "DELETE FROM room_finding_system.service_house WHERE houseid= ?1 ",nativeQuery = true)
    void deleteByHouseId(int houseid);

}
