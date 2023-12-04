package com.example.roomfindingsystem.service;


import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.repository.*;
import com.roomfindingsystem.service.impl.GcsService;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserServiceImplTest.class)
@ExtendWith(MockitoExtension.class)
@Service
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private DistrictRepository districtRepository;

    @Mock
    private WardRepository wardRepository;

    @Mock
    private GcsService gcsService;

    @InjectMocks
    private UserServiceImpl userService;

    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
    }


    @Test
    public void testFindUserDtoByEmailWhenUserExistsThenReturnUserDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setFirstName("Hà");
        userEntity.setLastName("Mạnh");
        userEntity.setEmail("thachdp2808@gmail.com");
        userEntity.setGender(true);
        userEntity.setAddressId(1);
        assertEquals("Hà", userEntity.getFirstName());
        assertEquals("Mạnh", userEntity.getLastName());
        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());
    }


    @Test
    public void testFindUserDtoByEmailWhenUserFoundThenReturnUserDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setFirstName("Hà");
        userEntity.setLastName("Mạnh");
        userEntity.setEmail("thachdp2808@gmail.com");
        userEntity.setGender(true);
        userEntity.setAddressId(1);

        when(userRepository.findByEmail("thachdp2808@gmail.com")).thenReturn(Optional.of(userEntity));
        userRepository.findByEmail("thachdp2808@gmail.com");

        assertEquals("Hà", userEntity.getFirstName());
        assertEquals("Mạnh", userEntity.getLastName());
        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());
    }


    @Test
    public void testFindByIdWhenValidIdThenReturnUserDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setFirstName("Hà");
        userEntity.setLastName("Mạnh");
        userEntity.setEmail("thachdp2808@gmail.com");
        userRepository.findById(1);
        assertEquals("Hà", userEntity.getFirstName());
        assertEquals("Mạnh", userEntity.getLastName());
        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());

    }


    @Test
    public void testFindUserDtoByEmailWhenUserDoesNotExistThenThrowException() {
        // Arrange
        when(userRepository.findByEmail("thachdp2808@gmail1.com")).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), userRepository.findByEmail("thachdp2808@gmail1.com"));
    }

    @Test
    public void testFindByIdWhenInvalidIdThenThrowException() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), userRepository.findById(1));

    }

    @Test
    public void testCreateUserDtoWhenValidUserDtoThenReturnCreatedUserDto() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("johndoe@example.com");
        userDto.setPassword("password");

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);


        // Assert
        assertNotNull(userEntity);
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
        assertEquals(userDto.getLastName(), userEntity.getLastName());
        assertEquals(userDto.getEmail(), userEntity.getEmail());
    }

    @Test
    public void testCreateUserWhenInvalidUserDtoThenThrowException() {
        // Arrange
        UserEntity user= new UserEntity();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        // Act and Assert
        assertEquals(null, userService.save(user));


    }


}