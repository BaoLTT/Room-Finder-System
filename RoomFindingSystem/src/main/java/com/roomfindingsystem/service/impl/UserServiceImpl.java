package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.reponsitory.UserReponsitory;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.vo.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserReponsitory userRepository;


    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void registerUser(UserDto userDto) {
        userRepository.save(userDto);
    }

    @Override
    public UserEntity getUserByRoomId(int roomId) {
        return userRepository.findUserByRoomId(roomId);
    }


}
