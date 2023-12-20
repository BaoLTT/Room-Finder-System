package com.roomfindingsystem.repository;
import com.roomfindingsystem.entity.DistrictEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface DistrictRepository extends CrudRepository<DistrictEntity, Integer>, JpaRepository<DistrictEntity, Integer> {
    List<DistrictEntity> findByProvinceId(Integer provinceId);

    @Query(value = "SELECT * FROM room_finding_system.district a where a.districtid = ?1 ",nativeQuery = true)
    DistrictEntity getDistrictById(Integer id);
}
