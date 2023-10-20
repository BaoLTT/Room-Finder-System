package com.roomfindingsystem.service;

import com.roomfindingsystem.config.SecurityUser;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.reponsitory.UserReponsitory;
import com.roomfindingsystem.vo.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public interface UserService extends UserDetailsService {
    void saveUser(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
    void registerUser(UserDto userDto);

    UserEntity getUserByRoomId(int roomId);

    UserEntity save(UserEntity user);
    int recoverPassword(String password, String email);

}

