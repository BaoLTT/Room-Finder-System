package com.roomfindingsystem.reponsitory;
import com.roomfindingsystem.entity.ProvinceEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@SpringBootApplication
public interface ProvinceRepository extends CrudRepository<ProvinceEntity, Integer>, JpaRepository<ProvinceEntity, Integer> {

}
