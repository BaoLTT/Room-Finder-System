package com.roomfindingsystem.reponsitory;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.vo.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReponsitory extends CrudRepository<UserEntity, Integer>, JpaRepository<UserEntity, Integer> {
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


}
