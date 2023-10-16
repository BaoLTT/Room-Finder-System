//package com.roomfindingsystem.service.impl;
//
//import com.roomfindingsystem.config.SecurityUser;
//import com.roomfindingsystem.entity.UserEntity;
//import com.roomfindingsystem.reponsitory.UserReponsitory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//
//@Service
//public class UserService implements UserDetailsService {
//
//    @Autowired
//    private UserReponsitory userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<UserEntity> userByUsername = userRepository.findByEmail(username);
//        if (!userByUsername.isPresent()) {
//            System.out.println("Could not find user with that username: {}");
//            throw new UsernameNotFoundException("Invalid credentials!");
//        }
//        UserEntity user = userByUsername.get();
//        if (user == null || !user.getEmail().equals(username)) {
//            System.out.println("Could not find user with that username: {}");
//            throw new UsernameNotFoundException("Invalid credentials!");
//        }
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return String.valueOf(user.getRoleId());
//            }
//        });
//
//        return new SecurityUser(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities,
//                user.getFirstName(), user.getLastName(), user.getEmail(), user.getDob().toLocalDateTime().toLocalDate());
//    }
////    @Autowired
////    private UserService userService;
//
////    @Autowired
////    @Lazy
////    private PasswordEncoder passwordEncoder;
//
//    //chưa xử lý mã hóa code
////    @Override
////    public void saveUser(UserEntity user) {
////        userRepository.save(user);
////    }
////
////    @Override
////    public Optional<UserEntity> findByEmail(String email) {
////        return userRepository.findByEmail(email);
////    }
////
////    @Override
////    public void registerUser(UserDto userDto) {
////        userRepository.save(userDto);
////    }
////
////    @Override
////    public UserEntity getUserByRoomId(int roomId) {
////        return userRepository.findUserByRoomId(roomId);
////    }
////
////    @Override
////    public UserEntity save(UserEntity user) {
////        if(user.getPassword()!=null)
////            user.setPassword(passwordEncoder.encode(user.getPassword()));
////        return userRepository.save(user);
////    }
////
////
////    @Override
////    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
////        UserEntity user = userRepository.findByEmail(email)
////                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
////        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
////                Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+user.getRoleId())));
////    }
//
//
//}
