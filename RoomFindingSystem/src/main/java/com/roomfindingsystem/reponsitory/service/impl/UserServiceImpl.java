package com.roomfindingsystem.reponsitory.service.impl;

import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.reponsitory.*;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.vo.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserReponsitory userRepository;
    private final AddressRepository addressRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserReponsitory userRepository, AddressRepository addressRepository, ProvinceRepository provinceRepository, DistrictRepository districtRepository, WardRepository wardRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.wardRepository = wardRepository;
        this.modelMapper = modelMapper;
    }


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
    public UserDto findById(int id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        UserEntity user = optionalUser.get();
        UserDto userDto = modelMapper.map(user, UserDto.class);

        AddressEntity address = addressRepository.findById(user.getAddressId()).get();

        ProvinceEntity province = provinceRepository.findById(address.getProvinceAddress().getId()).get();
        DistrictEntity district = districtRepository.findById(address.getDistrictAddress().getId()).get();
        WardEntity ward = wardRepository.findById(address.getWardAddress().getId()).get();

        userDto.setProvince(province.getName());
        userDto.setDistrict(district.getName());
        userDto.setWard(ward.getName());
        userDto.setProvinceId(province.getId());
        userDto.setDistrictId(district.getId());
        userDto.setWardId(ward.getId());
        userDto.setAddressDetails(address.getAddressDetails());

        if(user.getGender()) {
            userDto.setGender("MALE");
        }
        else {
            userDto.setGender("FEMALE");
        }

        userDto.setDob(user.getDob().toString());

        System.out.println(userDto);

        return userDto;
    }

    @Override
    public void updateProfile(UserDto userDto, MultipartFile file) throws IOException {
        UserEntity user = userRepository.findById(userDto.getUserId()).get();

        UserEntity saveUser = new UserEntity();
        if (!file.isEmpty()) {
            //        Handle Image
            byte[] imageData = Base64.getEncoder().encode(file.getBytes());
            String imageLink = new String(imageData, StandardCharsets.UTF_8);
            saveUser.setImageLink(imageLink);
        } else {
            saveUser.setImageLink(user.getImageLink());
        }
//        Handle gender
        if (Objects.equals(userDto.getGender(), "MALE")) {
            saveUser.setGender(true);
        } else {
            saveUser.setGender(false);
        }
//            Handle when not edit address
        if (userDto.getProvinceId() != null && userDto.getDistrictId() != null && userDto.getWardId() != null) {
            //       Begin Handle Address
            Optional<AddressEntity> optionalAddress = addressRepository.findByProvinceAddressIdAndDistrictAddressIdAndWardAddressId(userDto.getProvinceId(), userDto.getDistrictId(), userDto.getWardId());
            AddressEntity address = new AddressEntity();
            if (optionalAddress.isEmpty()) {
//                Update toan bo
                ProvinceEntity province = provinceRepository.findById(userDto.getProvinceId()).get();
                address.setProvinceAddress(province);
                DistrictEntity district = districtRepository.findById(userDto.getDistrictId()).get();
                address.setDistrictAddress(district);
                WardEntity ward = wardRepository.findById(userDto.getWardId()).get();
                address.setWardAddress(ward);
                address.setAddressDetails(userDto.getAddressDetails());

            } else {
//                Chi update detail
                address = optionalAddress.get();
                address.setAddressDetails(userDto.getAddressDetails());
            }
            addressRepository.save(address);
            AddressEntity saveAddress = addressRepository.findByProvinceAddressIdAndDistrictAddressIdAndWardAddressId(userDto.getProvinceId(), userDto.getDistrictId(), userDto.getWardId()).get();

            saveUser.setAddressId(saveAddress.getId());
        }
        else {
            AddressEntity findAddress = addressRepository.findById(user.getAddressId()).get();
            Optional<AddressEntity> optionalAddress = addressRepository.findByProvinceAddressIdAndDistrictAddressIdAndWardAddressIdAndAddressDetails(findAddress.getProvinceAddress().getId(), findAddress.getDistrictAddress().getId(), findAddress.getWardAddress().getId(), userDto.getAddressDetails());
            if(optionalAddress.isEmpty()) {
                AddressEntity newAddress = new AddressEntity();

                newAddress.setProvinceAddress(findAddress.getProvinceAddress());
                newAddress.setDistrictAddress(findAddress.getDistrictAddress());
                newAddress.setWardAddress(findAddress.getWardAddress());
                newAddress.setAddressDetails(userDto.getAddressDetails());
                addressRepository.save(newAddress);
                newAddress = addressRepository.findByProvinceAddressIdAndDistrictAddressIdAndWardAddressIdAndAddressDetails(findAddress.getProvinceAddress().getId(), findAddress.getDistrictAddress().getId(), findAddress.getWardAddress().getId(), userDto.getAddressDetails()).get();
                saveUser.setAddressId(newAddress.getId());

            }
            else {
                saveUser.setAddressId(user.getAddressId());
            }
        }

//            Begin Mapping
//            UserDto:
        saveUser.setDob(LocalDate.parse(userDto.getDob()));
        saveUser.setEmail(userDto.getEmail());
        saveUser.setFirstName(userDto.getFirstName());
        saveUser.setLastName(userDto.getLastName());
        saveUser.setLastModifiedDate(LocalDate.now());
        saveUser.setPhone(userDto.getPhone());

//        User:
        saveUser.setUserId(user.getUserId());
        saveUser.setCreatedDate(user.getCreatedDate());
        saveUser.setFacebookId(user.getFacebookId());
        saveUser.setGmailId(user.getGmailId());
        saveUser.setPassword(user.getPassword());
        saveUser.setRoleId(user.getRoleId());
        saveUser.setUserStatusId(user.getUserStatusId());
        userRepository.save(saveUser);
        }

}
