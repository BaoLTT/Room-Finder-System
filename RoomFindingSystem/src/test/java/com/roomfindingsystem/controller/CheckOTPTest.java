package com.roomfindingsystem.controller;


import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.EmailSenderService;
import com.roomfindingsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckOTPTest {
    @InjectMocks
    private OtpController otpController;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;
//    private final EmailSenderService emailSenderService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckOtp_Successful() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(session.getAttribute("otp-register")).thenReturn("123456"); // Set a valid OTP
        when(session.getAttribute("userid")).thenReturn(1); // Set other required session attributes
        when(session.getAttribute("email")).thenReturn("test@example.com");

        // Act
        String result = otpController.checkOtp(session, "123456", model, request);

        // Assert
        verify(userService, times(1)).saveUser(any(UserEntity.class));
        assertEquals("redirect:/login", result);
    }

    @Test
    public void testCheckOtp_IncorrectOtp() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(session.getAttribute("otp-register")).thenReturn("123456"); // Set a valid OTP

        // Act
        String result = otpController.checkOtp(session, "wrongotp", model, request);

        // Assert
        verify(userService, never()).saveUser(any(UserEntity.class));
        assertEquals("otpConfirm", result);
        verify(model, times(1)).addAttribute("mess", "OTP is not correct! Please check your email.");

    }


}
