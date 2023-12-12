package com.roomfindingsystem.controller;



import com.roomfindingsystem.service.EmailSenderService;
import com.roomfindingsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ForgotResetPassTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private EmailSenderService emailSenderService;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMailForgotPass_Success() {
        // Arrange
        String email = "test@example.com";
        MockHttpSession mockSession = new MockHttpSession();
        Model model = mock(Model.class);

        // Act
        String result = userController.sendMailForgotPass(email, mockSession);

        // Assert
        verify(emailSenderService, times(1)).sendEmail(eq(email), any(String.class), any(String.class));
        verify(model, never()).addAttribute(eq("error"), anyString());

        // Assert the returned view name
        assert result.equals("recoverPage");
    }

    @Test
    public void testSendMailForgotPass_Exception() {
        // Arrange
        String email = "test@example.com";
        MockHttpSession mockSession = new MockHttpSession();
        Model model = mock(Model.class);
//        doThrow(new RuntimeException("Some exception")).when(emailSenderService).sendEmail(eq(email), any(String.class), any(String.class));

        // Act
        String result = userController.sendMailForgotPass(email, mockSession);

        // Assert
        verify(emailSenderService, times(1)).sendEmail(eq(email), any(String.class), any(String.class));
        verify(model, never()).addAttribute(eq("success"), anyString());

        // Assert the returned view name
        assert result.equals("recoverPage");
    }

    //change pass
    @Test
    void testSaveChangePass_Success() {
        // Arrange
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String encodedPassword = "encodedPassword";
        String email = "baoltthe153367@fpt.edu.vn";

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(email);



        // Act
        String result = userController.saveChangePass(oldPassword, newPassword, mock(Model.class));

        // Assert

        assertEquals("change-password-form", result);
    }

    @Test
    void testSaveChangePass_Fail() {
        // Arrange
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String encodedPassword = "encodedPassword";
        String email = "test@example.com";

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(email);



        // Act
        String result = userController.saveChangePass(oldPassword, newPassword, mock(Model.class));

        // Assert

        verify(userService, never()).recoverPassword(anyString(), anyString());
        assertEquals("change-password-form", result);
    }

}
