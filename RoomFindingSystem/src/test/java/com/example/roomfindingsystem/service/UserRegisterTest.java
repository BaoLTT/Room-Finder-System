package com.example.roomfindingsystem.service;

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
public class UserRegisterTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    @Test
    void testFindByEmail() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("baoltthe153367@fpt.com");

        // Giả định UserRepository trả về Optional chứa đối tượng UserEntity khi gọi findByEmail
        when(userRepository.findByEmail("baoltthe153367@fpt.com")).thenReturn(Optional.of(userEntity));

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail("baoltthe153367@fpt.com");

        // Kiểm tra kết quả
        assertTrue(result.isPresent());
        assertEquals("baoltthe153367@fpt.com", result.get().getEmail());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository).findByEmail("baoltthe153367@fpt.com");
    }

    @Test
    void testFindByEmail_NotFound() {
        // Giả định UserRepository trả về Optional rỗng khi gọi findByEmail
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Gọi phương thức cần kiểm thử từ service
        Optional<UserEntity> result = userService.findByEmail("nonexistent@example.com");

        // Kiểm tra kết quả
        assertFalse(result.isPresent());

        // Đảm bảo rằng phương thức findByEmail đã được gọi với đúng tham số
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }
}
