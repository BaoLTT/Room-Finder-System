package com.roomfindersystem.repository;
import com.roomfindersystem.entity.DistrictEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface DistrictRepository extends CrudRepository<DistrictEntity, Integer>, JpaRepository<DistrictEntity, Integer> {
    List<DistrictEntity> findByProvinceId(Integer provinceId);
}
