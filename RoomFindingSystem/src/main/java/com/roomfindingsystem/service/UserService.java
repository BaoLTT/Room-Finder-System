package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.vo.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void saveUser(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
    void registerUser(UserDto userDto);

    UserEntity getUserByRoomId(int roomId);


}
