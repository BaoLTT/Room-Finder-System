package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.vo.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public interface UserService extends UserDetailsService {
    void saveUser(UserEntity user);

    Optional<UserEntity> findByEmail(String email);

    void registerUser(UserDto userDto);

    UserEntity getUserByRoomId(int roomId);

    UserDto findById(int id);

    void updateProfile(UserDto userDto, MultipartFile file) throws IOException;

    UserEntity save(UserEntity user);

    int recoverPassword(String password, String email);

}

