package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Query(value = "SELECT * FROM room_finding_system.user",nativeQuery = true)
    List<UserEntity> findAll();
}
