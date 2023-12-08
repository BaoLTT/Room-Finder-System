package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.repository.UserRepository;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

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
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail("nonexistent@example.com");

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail("nonexistent@example.com");
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
    void testFindByEmail_EmailExceedsMaxLength() {
        // Test Case 5: Email có độ dài vượt quá giới hạn
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
        // Test Case 6: Email có định dạng không hợp lệ
        String invalidEmail = "invalid-email";

        when(userRepository.findByEmail(invalidEmail)).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail(invalidEmail);

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail(invalidEmail);
    }

    //test 7 đang test phân biệt chữ hoa với thường trong khí thường gmail vs outlook nó không phan biêt
    @Test
    void testFindByEmail_EmailCaseInsensitive() {
        // Test Case 7: Email tồn tại nhưng với ký tự hoa/thường khác nhau
        String originalEmail = "baoltthe153367@fpt.edu.vn";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(originalEmail);
        //giả định
        when(userRepository.findByEmail(originalEmail)).thenReturn(Optional.of(userEntity));

        String uppercaseEmail = "BAOLTTHE153367@FPT.EDU.VN";

        Optional<UserEntity> result = userService.findByEmail(uppercaseEmail);

        assertTrue(result.isPresent());
//        assertEquals(originalEmail, result.get().getEmail());
        verify(userRepository).findByEmail(originalEmail);
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
