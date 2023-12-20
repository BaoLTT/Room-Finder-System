package com.roomfindingsystem.repository;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.ProvinceEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@SpringBootApplication
public interface ProvinceRepository extends CrudRepository<ProvinceEntity, Integer>, JpaRepository<ProvinceEntity, Integer> {
    @Query(value = "SELECT * FROM room_finding_system.province a where a.provinceid = ?1 ",nativeQuery = true)
    ProvinceEntity getProvinceById(Integer id);

}
