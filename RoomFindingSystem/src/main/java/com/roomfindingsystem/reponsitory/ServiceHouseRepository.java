package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("serviceRepository")
public interface ServiceHouseRepository extends JpaRepository<ServiceDetailEntity, Integer> {

    List<ServiceDetailEntity> findAll();
}
