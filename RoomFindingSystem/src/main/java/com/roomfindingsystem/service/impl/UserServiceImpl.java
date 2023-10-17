package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.config.SecurityUser;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.reponsitory.UserReponsitory;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.vo.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReponsitory userRepository;


    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

//    chưa xử lý mã hóa code
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

    @Override
    public UserEntity save(UserEntity user) {
        if(user.getPassword()!=null)
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userByUsername = userRepository.findByEmail(username);
        if (!userByUsername.isPresent()) {
            System.out.println("Could not find user with that username: {}");
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        UserEntity user = userByUsername.get();
        if (user == null || !user.getEmail().equals(username)) {
            System.out.println("Could not find user with that username: {}");
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleId())); // Ví dụ, user.getRoleName() trả về tên của vai trò

        return new SecurityUser(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities,
                user.getFirstName(), user.getLastName(), user.getEmail());
    }


}
