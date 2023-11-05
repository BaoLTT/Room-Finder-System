package com.roomfindingsystem.repository;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SpringBootApplication
public interface ServiceDetailRepository extends CrudRepository<ServiceDetailEntity, Integer>, JpaRepository<ServiceDetailEntity, Integer> {
    Optional<ServiceDetailEntity> findByServiceName(String name);

    @Query(value = "SELECT * FROM room_finding_system.service_detail",nativeQuery = true)
    List<ServiceDetailEntity> getAll();
}
