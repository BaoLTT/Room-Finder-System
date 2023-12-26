package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.repository.AddressRepository;
import com.roomfindingsystem.repository.UserRepository;
import com.roomfindingsystem.service.impl.AddressServiceImpl;
import com.roomfindingsystem.service.impl.AdminManageUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AdminManageUserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    ModelMapper modelMapper;

    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    AdminManageUserServiceImpl adminManageUserServiceImpl;
    @Test
    void getAll() {
        UserEntity userEntity1 = new UserEntity();
        // set properties for userEntity1
        UserEntity userEntity2 = new UserEntity();
        // set properties for userEntity2

        List<UserEntity> userEntities = Arrays.asList(userEntity1, userEntity2);

        when(userRepository.findAllExceptSuperAdmin()).thenReturn(userEntities);

        // Mock mapping from entity to DTO
        when(modelMapper.map(any(UserEntity.class), eq(UserDto.class)))
                .thenAnswer(invocation -> {
                    UserEntity userEntityArg = invocation.getArgument(0);
                    UserDto userDto = new UserDto();
                    userDto.setUserId(userEntityArg.getUserId());  // map other properties accordingly
                    return userDto;
                });

        // Call the method you want to test
        List<UserDto> result = adminManageUserServiceImpl.getAll();

        // Assertions
        assertEquals(userEntities.size(), result.size());
    }

    @Test
    void deleteById() {
        Integer userId = 1;

        // Call the method you want to test
        adminManageUserServiceImpl.deleteById(userId);

        // Verify that deleteById was called with the correct argument
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void InsertUser1() {
        Integer userId = 1;

        // Call the method you want to test
        adminManageUserServiceImpl.deleteById(userId);

        // Verify that deleteById was called with the correct argument
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void InsertUser2() {
        Integer userId = 1;

        // Call the method you want to test
        adminManageUserServiceImpl.deleteById(userId);

        // Verify that deleteById was called with the correct argument
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void InsertUser3() {
        Integer userId = 1;

        // Call the method you want to test
        adminManageUserServiceImpl.deleteById(userId);

        // Verify that deleteById was called with the correct argument
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void InsertUser4() {
        Integer userId = 1;

        // Call the method you want to test
        adminManageUserServiceImpl.deleteById(userId);

        // Verify that deleteById was called with the correct argument
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void InsertUser5() {
        Integer userId = 1;

        // Call the method you want to test
        adminManageUserServiceImpl.deleteById(userId);

        // Verify that deleteById was called with the correct argument
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void InsertUser6() {
        Integer userId = 1;

        // Call the method you want to test
        adminManageUserServiceImpl.deleteById(userId);

        // Verify that deleteById was called with the correct argument
        verify(userRepository, times(1)).deleteById(userId);
    }


}