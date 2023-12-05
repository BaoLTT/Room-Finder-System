package com.roomfindingsystem.controller;

import com.roomfindingsystem.controller.UserController;
import com.roomfindingsystem.dto.Smsrequest;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.EmailSenderService;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.service.impl.EmailSenderServiceImpl;
import com.roomfindingsystem.service.impl.Smsservice;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserRegisterControllerTest {

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

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_ValidUser() {
        // Chuẩn bị
        UserEntity user = new UserEntity();
        user.setEmail("thaibaoa3k45no123@gmail.com");


        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.findByEmail("thaibaoa3k45no123@gmail.com")).thenReturn(Optional.empty());

        // Thực hiện
        String result = userController.save(user, bindingResult, model, null,session);

        // Kiểm tra
        verify(userService).findByEmail("thaibaoa3k45no123@gmail.com");
        verify(model, never()).addAttribute(eq("mess"), anyString());
        verify(session).setAttribute(eq("otp-register"), anyString());
        verify(session).setMaxInactiveInterval(360);
        // Kiểm tra các phương thức khác nếu cần

        // Kiểm tra kết quả
        assertEquals("redirect:/otp-check", result);
        // Thêm các kiểm tra khác tùy thuộc vào cần thiết
    }



    @Test
    public void testSave_UserWithExistingEmail() {
        // Chuẩn bị
        UserEntity user = new UserEntity();
        user.setEmail("baoltthe153367@fpt.edu.vn"); // Thiết lập email đã tồn tại

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.findByEmail("baoltthe153367@fpt.edu.vn")).thenReturn(Optional.of(user));

        // Thực hiện
        String result = userController.save(user, bindingResult, model, null, session);

        // Kiểm tra
        assertEquals("register", result);
        verify(model).addAttribute(eq("mess"), anyString());
        verify(emailSenderService, never()).sendEmail(any(), any(), any());
    }

    @Test
    public void testSave_InvalidUser() {
        // Chuẩn bị
        UserEntity user = new UserEntity();
        user.setEmail("invalid-email"); // Thiết lập email không hợp lệ

        when(bindingResult.hasErrors()).thenReturn(true);

        // Thực hiện
        String result = userController.save(user, bindingResult, model, null, session);

        // Kiểm tra
        assertEquals("register", result);
        verifyNoInteractions(userService, emailSenderService, session);
    }

    // Thêm các test case khác cho các tình huống khác nhau
}
