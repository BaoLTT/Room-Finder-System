package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.repository.AddressRepository;
import com.roomfindingsystem.repository.UserRepository;
import com.roomfindingsystem.service.AdminManageUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminManageUserServiceImpl implements AdminManageUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    private final GcsService gcsService;

    public AdminManageUserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AddressRepository addressRepository, GcsService gcsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.gcsService = gcsService;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(user -> {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            if(user.getGender()) {
                userDto.setGender("MALE");
            }
            else {
                userDto.setGender("FEMALE");
            }

            if(user.getUserStatusId() == 1) {
                userDto.setStatus("ACTIVE");
            }
            else {
                userDto.setStatus("INACTIVE");
            }
            userDto.setDob(user.getDob().toString());
            return userDto;
        }).toList();
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void insertUser(UserDto userDto, MultipartFile file) throws IOException {
        UserEntity saveUser = new UserEntity();

//        Handle gender
        if (Objects.equals(userDto.getGender(), "MALE")) {
            saveUser.setGender(true);
        } else {
            saveUser.setGender(false);
        }
//            Handle when not edit address
        if (userDto.getProvinceId() != null && userDto.getDistrictId() != null && userDto.getWardId() != null) {
            //       Begin Handle Address
            Optional<AddressEntity> optionalAddress = addressRepository.findByProvinceIdAndDistrictIdAndWardId(userDto.getProvinceId(), userDto.getDistrictId(), userDto.getWardId());
            AddressEntity address = new AddressEntity();
            if (optionalAddress.isEmpty()) {
//                Update toan bo
                address.setProvinceId(userDto.getProvinceId());
                address.setDistrictId(userDto.getDistrictId());
                address.setWardId(userDto.getWardId());
                address.setAddressDetails(userDto.getAddressDetails());

            } else {
//                Chi update detail
                address = optionalAddress.get();
                address.setAddressDetails(userDto.getAddressDetails());
            }
            addressRepository.save(address);
            AddressEntity saveAddress = addressRepository.findByProvinceIdAndDistrictIdAndWardId(userDto.getProvinceId(), userDto.getDistrictId(), userDto.getWardId()).get();

            saveUser.setAddressId(saveAddress.getAddressId());
        }
//            Begin Mapping
//            UserDto:
        saveUser.setDob(LocalDate.parse(userDto.getDob()));
        saveUser.setEmail(userDto.getEmail());
        saveUser.setFirstName(userDto.getFirstName());
        saveUser.setLastName(userDto.getLastName());
        saveUser.setLastModifiedDate(Timestamp.from(Instant.now()));
        saveUser.setPhone(userDto.getPhone());

//        User:
        saveUser.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        saveUser.setRoleId(userDto.getRole());
        if(Objects.equals(userDto.getStatus(), "ACTIVE")) {
            saveUser.setUserStatusId(1);
        }
        else {
            saveUser.setUserStatusId(0);
        }
        userRepository.save(saveUser);

        if (!file.isEmpty()) {
            //        Handle Image
            byte[] imageBytes = file.getBytes();
            gcsService.uploadImage("rfs_bucket", "User/user_"+saveUser.getUserId()+".jpg", imageBytes);
            saveUser.setImageLink("https://storage.cloud.google.com/rfs_bucket/User/"+"user_"+saveUser.getUserId()+".jpg");
            userRepository.save(saveUser);
        }
    }
}
