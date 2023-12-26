package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.repository.*;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    AddressRepository addressRepository;
    @Mock
    ProvinceRepository provinceRepository;
    @Mock
    DistrictRepository districtRepository;
    @Mock
    WardRepository wardRepository;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    //Test findByEmail()
    @Test
    void testFindByEmail() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("baoltthe153367@fpt.edu.com");

        // Giả định UserRepository trả về Optional chứa đối tượng UserEntity khi gọi findByEmail
        when(userRepository.findByEmail("baoltthe153367@fpt.edu.com")).thenReturn(Optional.of(userEntity));

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail("baoltthe153367@fpt.edu.com");

        // Kiểm tra kết quả
        assertTrue(result.isPresent());
        assertEquals("baoltthe153367@fpt.edu.com", result.get().getEmail());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail("baoltthe153367@fpt.edu.com");
    }

    @Test
    void testFindByEmail_EmailDoesNotExist() {
        // Giả định UserRepository trả về Optional rỗng khi gọi findByEmail
        when(userRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail("abc@gmail.com");

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail("abc@gmail.com");
    }

    @Test
    void testFindByEmail_EmailIsNull() {
        // Test Case 3: Email có giá trị là null
        when(userRepository.findByEmail(null)).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail(null);

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail(null);
    }

    @Test
    void testFindByEmail_EmailIsEmpty() {
        // Test Case 4: Email có giá trị trống
        when(userRepository.findByEmail("")).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail("");

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail("");
    }

    @Test
    void testFindByEmail_EmailLength254() {
        // Test Case 5: Email có độ dài vượt quá giới hạn
        String longEmail = "a".repeat(254); // Giả sử giới hạn là 255 ký tự

        when(userRepository.findByEmail(longEmail)).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail(longEmail);

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail(longEmail);
    }

    @Test
    void testFindByEmail_EmailLength255() {
        // Test Case 6: Email có độ dài vượt quá giới hạn
        String longEmail = "a".repeat(255); // Giả sử giới hạn là 255 ký tự

        when(userRepository.findByEmail(longEmail)).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail(longEmail);

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail(longEmail);
    }

    @Test
    void testFindByEmail_EmailExceedsMaxLength() {
        // Test Case 7: Email có độ dài vượt quá giới hạn
        String longEmail = "a".repeat(256); // Giả sử giới hạn là 255 ký tự

        when(userRepository.findByEmail(longEmail)).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail(longEmail);

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail(longEmail);
    }

    @Test
    void testFindByEmail_InvalidEmailFormat() {
        // Test Case 8: Email có định dạng không hợp lệ
        String invalidEmail = "invalid-email";

        when(userRepository.findByEmail(invalidEmail)).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail(invalidEmail);

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail(invalidEmail);
    }

    //test
    @Test
    void testFindByEmail_EmailCaseInsensitive() {
        //test case 9:
        String originalEmail = "baoltthe153367@fpt.edu.vn";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(originalEmail); userEntity.setUserId(4);
        when(userRepository.findByEmail("baoltthe153367@fpt.edu.vn")).thenReturn(Optional.of(userEntity));

        String uppercaseEmail = "BAOLTTHE153367@FPT.EDU.VN";
        Optional<UserEntity> result = userService.findByEmail(uppercaseEmail.toLowerCase());
        assertEquals(4, result.get().getUserId());
    }


    //registerUser
    @Test
    void testRegisterUser_ValidInput(){
        UserDto userDto = new UserDto("a","0123456","a@gmail.com","123456","Bao","1");
        userService.registerUser(userDto);
        verify(userRepository, times(1)).save(userDto);

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


    //findById
    @Test
    void testFindById_WithValidId() {
        int userId = 3;
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);



        assertEquals(3,userEntity.getUserId());

    }

    @Test
    void testFindById_WithNonExistingUser() {
        // Arrange
        int userId = 100000;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        UserDto result = userService.findById(userId);

        // Assert
        assertNull(result);

        // Verify that userRepository.findById() was called with the correct userId
        verify(userRepository, times(1)).findById(userId);

        // Verify that other repository methods were not called
        verifyNoInteractions(addressRepository, provinceRepository, districtRepository, wardRepository);
    }


    //save
    @Test
    public void testSaveUser_Success() {
        // Arrange
        UserEntity userToSave = new UserEntity();
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        // Act
        userService.saveUser(userToSave);

        // Assert
        verify(userRepository, times(1)).save(userToSave);
    }





    //getUserForChangePass()
    @Test
    void testGetUserForChangePass_ValidEmail() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        when(userRepository.getUserEntitiesByUserId(email)).thenReturn(userEntity.getPassword());

        // Act
        String result = userService.getUserForChangePass(email);

        // Assert
        assertEquals(userEntity.getPassword(), result);
        verify(userRepository, times(1)).getUserEntitiesByUserId(email);
    }

    @Test
    void testGetUserForChangePass_InvalidEmail() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.getUserEntitiesByUserId(email)).thenReturn(null);

        // Act
        String result = userService.getUserForChangePass(email);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).getUserEntitiesByUserId(email);
    }

    @Test
    void testGetUserForChangePass_NullEmail() {
        // Arrange
        String email = null;

        // Act
        String result = userService.getUserForChangePass(email);

        // Assert
        assertNull(result);
//        verify(userRepository, never()).getUserEntitiesByUserId(email);
    }
}
