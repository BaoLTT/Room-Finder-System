package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.vo.UserDto;

import com.roomfindingsystem.dto.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@SpringBootApplication

public interface UserReponsitory extends JpaRepository<UserEntity, Integer> {

    public UserEntity save(UserEntity user);

    Optional<UserEntity> findByEmail(String email);

    public UserDto save(UserDto userDto);

    @Query("SELECT u FROM UserEntity u " +
            "JOIN HousesEntity h " +
            "on h.userId = u.userId " +
            "JOIN RoomEntity r " +
            "on r.houseId = h.houseId " +
            "WHERE r.roomId = :roomid")
    UserEntity findUserByRoomId(int roomid);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity as u set u.password =?1 where u.email=?2")
    int updatePassword(String password, String email);

    @Query("select u.password from UserEntity u where u.email=?1")
    String getUserEntitiesByUserId(String email);

}
