package com.example.roomfindingsystem.controller;

import com.roomfindingsystem.controller.UserController;
import com.roomfindingsystem.dto.Smsrequest;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.impl.EmailSenderServiceImpl;
import com.roomfindingsystem.service.impl.Smsservice;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserRegisterControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private EmailSenderServiceImpl emailSenderService;



    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Test
    public void testSave() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setEmail("baoltthe153367@fpt.edu.vn");
        // Populate other fields of the user object as needed

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());

        // You may need to mock other dependencies or set up session attributes as needed

        // Act
        String result = userController.save(user, bindingResult, model, null, session);

        // Assert
        assertEquals("redirect:/otp-check", result);

        // Verify that the userService.findByEmail method is called with the correct argument
        verify(userService).findByEmail("test@example.com");

        // Verify that the emailSenderService.sendEmail method is called with the correct arguments
        verify(emailSenderService).sendEmail(eq("test@example.com"), anyString(), anyString());

        // You can add more assertions or verifications based on your requirements
    }
}
