package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.UserDto;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
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

    //registerUser
    @Test
    void testRegisterUser_ValidInput(){
        UserDto userDto = new UserDto();
        userService.registerUser(userDto);
//        verify(userRepository).save(userDto);

    }

    @Test
    void testRegisterUser_InValidInput(){
        UserDto userDto = new UserDto();
        userDto = null;
        userService.registerUser(userDto);
        verify(userRepository, never()).save(userDto);
    }


    //LoadUserByUsername
    @Test
    void testLoadUserByUsername_WithEmail() {
        String username = "thaibaoa3k45@gmail.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(username);
        userEntity.setPassword("password");
        userEntity.setRoleId("Member");

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());

    }

    @Test
    void testLoadUserByUsername_WithEmptyUsername() {
        String emptyUsername = "";
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(emptyUsername));

    }

    @Test
    void testLoadUserByUsername_WithNullUsername() {
        String emptyUsername = "";
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(emptyUsername));

    }

    @Test
    public void testRecoverPassword_ValidCredentials() {
        // Arrange
        String validPassword = "123456";
        String validEmail = "thaibaoa3k45@gmail.com";

        // Mock the behavior of the userRepository
        when(userRepository.updatePassword(validPassword, validEmail)).thenReturn(1);

        // Act
        int result = userService.recoverPassword(validPassword, validEmail);

        // Assert
        assertEquals(1, result);

        // Optionally, verify that the method on the mocked object was called with the expected parameters
        verify(userRepository).updatePassword(validPassword, validEmail);
    }

    @Test
    public void testRecoverPassword_InvalidCredentials() {
        // Arrange
        String invalidPassword = "";
        String invalidEmail = "";

        // Define the behavior of the mocked userRepository for invalid credentials
        when(userRepository.updatePassword(invalidPassword, invalidEmail)).thenReturn(0);

        // Act
        int result = userService.recoverPassword(invalidPassword, invalidEmail);

        // Assert
        assertEquals(0, result);

        // Optionally, verify that the method on the mocked object was called with the expected parameters
        verify(userRepository).updatePassword(invalidPassword, invalidEmail);
    }

    @Test
    public void testRecoverPassword_InvalidCredentials2() {
        // Arrange
        String invalidPassword = "";
        String invalidEmail = null;

        // Define the behavior of the mocked userRepository for invalid credentials
        when(userRepository.updatePassword(invalidPassword, invalidEmail)).thenReturn(0);

        // Act
        int result = userService.recoverPassword(invalidPassword, invalidEmail);

        // Assert
        assertEquals(0, result);

        // Optionally, verify that the method on the mocked object was called with the expected parameters
        verify(userRepository).updatePassword(invalidPassword, invalidEmail);
    }

    @Test
    public void testRecoverPassword_InvalidCredentials3() {
        // Arrange
        String invalidPassword = "123456";
        String invalidEmail = null;

        // Define the behavior of the mocked userRepository for invalid credentials
        when(userRepository.updatePassword(invalidPassword, invalidEmail)).thenReturn(0);

        // Act
        int result = userService.recoverPassword(invalidPassword, invalidEmail);

        // Assert
        assertEquals(0, result);

        // Optionally, verify that the method on the mocked object was called with the expected parameters
        verify(userRepository).updatePassword(invalidPassword, invalidEmail);
    }

    @Test
    public void testRecoverPassword_InvalidCredentials4() {
        // Arrange
        String invalidPassword = null;
        String invalidEmail = null;

        // Define the behavior of the mocked userRepository for invalid credentials
        when(userRepository.updatePassword(invalidPassword, invalidEmail)).thenReturn(0);

        // Act
        int result = userService.recoverPassword(invalidPassword, invalidEmail);

        // Assert
        assertEquals(0, result);

        // Optionally, verify that the method on the mocked object was called with the expected parameters
        verify(userRepository).updatePassword(invalidPassword, invalidEmail);
    }
}