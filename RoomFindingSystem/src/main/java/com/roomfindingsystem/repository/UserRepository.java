package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.UserEntity;



import com.roomfindingsystem.dto.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    public UserEntity save(UserEntity user);
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.userStatusId =1")
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    Optional<UserEntity> findByEmailWithoutStatus(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.phone = ?1 AND u.userStatusId =1")
    Optional<UserEntity> findByPhone(String phone);
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

    @Query(value = "SELECT * FROM room_finding_system.user where user.roleid= ?1 and user.user_statusid=1 ",nativeQuery = true)
    List<UserEntity> findUserByRole(String role);
    @Query("select count(*) from UserEntity u where u.roleId = 'Landlord' or u.roleId = 'User'")
    int countUserInAdmin();

    @Query("SELECT u FROM UserEntity u WHERE u.roleId <> 'SUPER_ADMIN'")
    List<UserEntity> findAllExceptSuperAdmin();
}
