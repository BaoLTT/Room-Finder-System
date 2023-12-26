package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.config.SecurityUser;
import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.repository.*;
import com.roomfindingsystem.service.UserService;


import com.roomfindingsystem.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final ModelMapper modelMapper;

    private final GcsService gcsService;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, ProvinceRepository provinceRepository, DistrictRepository districtRepository, WardRepository wardRepository, ModelMapper modelMapper, GcsService gcsService) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.wardRepository = wardRepository;
        this.modelMapper = modelMapper;
        this.gcsService = gcsService;
    }

//    public UserServiceImpl(){};

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
    public Optional<UserEntity> findByEmailWithoutStatus(String email){
        return userRepository.findByEmailWithoutStatus(email);
    }

    @Override
    public void registerUser(UserDto userDto) {
        if(userDto==null) {
            return ;
        }
        userRepository.save(userDto);
    }

    @Override
    public UserEntity getUserByRoomId(int roomId) {
        return userRepository.findUserByRoomId(roomId);
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getPassword() != null)
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userByEmail = userRepository.findByEmail(username);
        Optional<UserEntity> userByPhone = userRepository.findByPhone(username);

        UserEntity user;
        if (userByEmail.isPresent()) {
            user = userByEmail.get();
        } else if (userByPhone.isPresent()) {
            user = userByPhone.get();
        } else {
            System.out.println("Could not find user with that username: " + username);
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleId())); // Ví dụ, user.getRoleName() trả về tên của vai trò

        return new SecurityUser(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities,
                user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public UserDto findById(int id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        UserEntity user = optionalUser.get();
        UserDto userDto = modelMapper.map(user, UserDto.class);

        if(user.getAddressId()!=0){
            AddressEntity address = addressRepository.findById(user.getAddressId()).get();
            ProvinceEntity province = provinceRepository.findById(address.getProvinceId()).get();
            DistrictEntity district = districtRepository.findById(address.getDistrictId()).get();
            WardEntity ward = wardRepository.findById(address.getWardId()).get();

            userDto.setProvince(province.getName());
            userDto.setDistrict(district.getName());
            userDto.setWard(ward.getName());
            userDto.setProvinceId(province.getProvinceId());
            userDto.setDistrictId(district.getDistrictId());
            userDto.setWardId(ward.getWardId());
            userDto.setAddressDetails(address.getAddressDetails());
        }else{
            userDto.setProvinceId(0);
            userDto.setDistrictId(0);
            userDto.setWardId(0);
        }

        if (user.getGender() != null) {
            if (user.getGender()) {
                userDto.setGender("MALE");
            } else {
                userDto.setGender("FEMALE");
            }
        }
        if (user.getDob() != null) {
            userDto.setDob(user.getDob());
        }
        if (user.getUserStatusId() != null) {
            if (user.getUserStatusId() == 1) {
                userDto.setStatus("ACTIVE");
            } else {
                userDto.setStatus("INACTIVE");
            }
        }
        return userDto;
    }



    @Override
    public void updateProfile(UserDto userDto, MultipartFile file) throws IOException {
        UserEntity user = userRepository.findById(userDto.getUserId()).get();

        UserEntity saveUser = new UserEntity();
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));
        if (!file.isEmpty()) {
            //        Handle Image
            byte[] imageBytes = file.getBytes();
            gcsService.uploadImage("rfs_bucket", "User/user_" + user.getUserId()+formattedTimestamp + ".jpg", imageBytes);
            saveUser.setImageLink("/rfs_bucket/User/" + "user_" + user.getUserId()+formattedTimestamp + ".jpg");
        } else {
            saveUser.setImageLink(user.getImageLink());
        }
//        Handle gender
        if (Objects.equals(userDto.getGender(), "MALE")) {
            saveUser.setGender(true);
        } else {
            saveUser.setGender(false);
        }

        saveUser.setAddressId(userDto.getAddressID());
//            Begin Mapping
//            UserDto:
        saveUser.setDob(userDto.getDob());
        saveUser.setEmail(userDto.getEmail());
        saveUser.setFirstName(userDto.getFirstName());
        saveUser.setLastName(userDto.getLastName());
        saveUser.setLastModifiedDate(LocalDate.now());
        saveUser.setPhone(userDto.getPhone());
        saveUser.setRoleId(userDto.getRole());

//        User:
        saveUser.setUserId(user.getUserId());
        saveUser.setCreatedDate(user.getCreatedDate());
        saveUser.setGmailId(user.getGmailId());
        saveUser.setPassword(user.getPassword());

        if (Objects.equals(userDto.getStatus(), "ACTIVE")) {
            saveUser.setUserStatusId(1);
        } else {
            saveUser.setUserStatusId(0);
        }
        userRepository.save(saveUser);
    }

    @Override
    public int recoverPassword(String password, String email) {
        return userRepository.updatePassword(password, email);
    }

    @Override
    public String getUserForChangePass(String email) {
        return userRepository.getUserEntitiesByUserId(email);
    }

    @Override
    public int countUserInAdmin() {
        return userRepository.countUserInAdmin();
    }

    @Override
    public UserDto findUserDtoByEmail(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        UserEntity user = optionalUser.get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        if(user.getAddressId()!=0){
            AddressEntity address = addressRepository.findById(user.getAddressId()).get();
            ProvinceEntity province = provinceRepository.findById(address.getProvinceId()).get();
            DistrictEntity district = districtRepository.findById(address.getDistrictId()).get();
            WardEntity ward = wardRepository.findById(address.getWardId()).get();

            userDto.setProvince(province.getName());
            userDto.setDistrict(district.getName());
            userDto.setWard(ward.getName());
            userDto.setProvinceId(province.getProvinceId());
            userDto.setDistrictId(district.getDistrictId());
            userDto.setWardId(ward.getWardId());
            userDto.setAddressDetails(address.getAddressDetails());
        }else{
            userDto.setProvinceId(0);
            userDto.setDistrictId(0);
            userDto.setWardId(0);
        }

        userDto.setRole(user.getRoleId());
        if (user.getGender() != null) {
            if (user.getGender()) {
                userDto.setGender("MALE");
            } else {
                userDto.setGender("FEMALE");
            }
        }
        if (user.getDob() != null) {
            userDto.setDob(user.getDob());
        }
        return userDto;
    }

}