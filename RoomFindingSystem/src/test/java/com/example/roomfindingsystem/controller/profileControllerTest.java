package com.example.roomfindingsystem.controller;

import com.roomfindingsystem.controller.UserController;
import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.service.impl.EmailSenderServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.security.core.context.SecurityContext;

import java.io.IOException;


@ExtendWith(MockitoExtension.class)
public class profileControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private EmailSenderServiceImpl emailSenderService;

    @Mock
    private HttpSession session;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetProfilePageAuthenticated() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//        Authentication authentication = mock(Authentication.class);
//        SecurityContextHolder.setContext(mock(SecurityContext.class));
//        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
//        when(authentication.isAuthenticated()).thenReturn(true);
//        when(authentication.getName()).thenReturn("test@example.com");
//        UserDto userDto = new UserDto();
//        when(userService.findUserDtoByEmail("test@example.com")).thenReturn(userDto);
//
//        // Act
//        String result = userController.getProfilePage(model);
//
//        // Assert
//        assertEquals("profile", result);
//        verify(model).addAttribute("user", userDto);
//    }

//    @Test
//    void testGetProfilePageNotAuthenticated() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//        SecurityContextHolder.setContext(mock(SecurityContext.class));
//        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(null);
//
//        // Act
//        String result = userController.getProfilePage(model);
//
//        // Assert
//        assertEquals("redirect:/login", result);
//        verifyNoInteractions(userService);
//    }

    @Test
    void testUpdateUser() throws IOException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        UserDto userDto = new UserDto();
        MultipartFile file = new MockMultipartFile("file", new byte[0]);

        // Act
        String result = userController.updateUser(userDto, file);

        // Assert
        assertEquals("redirect:/profile", result);
        verify(userService).updateProfile(userDto, file);
    }


//    @Test
//    void testGetProfilePageWithUserDto() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//        UserDto userDto = new UserDto();
//        userDto.setUserId(1);
//        userDto.setFirstName("John");
//        userDto.setLastName("Doe");
//        userDto.setEmail("john.doe@example.com");
//        when(userService.findUserDtoByEmail("test@example.com")).thenReturn(userDto);
//        // Act
//        String result = userController.getProfilePage(model);
//
//        // Assert
////        assertEquals("redirect:/profile", result);
//        verify(model).addAttribute("user", userDto);
//    }

    @Test
    void testUpdateUserWithFile() throws IOException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        UserDto userDto = new UserDto();
        MultipartFile file = new MockMultipartFile("file", new byte[0]);

        // Act
        String result = userController.updateUser(userDto, file);

        // Assert
        assertEquals("redirect:/profile", result);
        verify(userService).updateProfile(userDto, file);
    }

}
