package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.RoomTypeEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@SpringBootApplication
public interface RoomTypeRepository extends CrudRepository<RoomTypeEntity, Integer> {
}
