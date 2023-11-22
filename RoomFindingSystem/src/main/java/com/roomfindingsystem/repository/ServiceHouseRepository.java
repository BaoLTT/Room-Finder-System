package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.entity.ServiceHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("serviceRepository")
public interface ServiceHouseRepository extends JpaRepository<ServiceHouseEntity, Integer> {
}
