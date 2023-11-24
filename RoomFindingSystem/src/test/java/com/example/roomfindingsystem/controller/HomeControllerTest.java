package com.example.roomfindingsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.roomfindingsystem.controller.HomeController;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    private HouseService houseService;

    @Mock
    private RoomService roomService;

    @Mock
    private UserService userService;

    //mock các service khác

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController controller;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void viewHomepage() {

        UserEntity user = new UserEntity();

        when(userService.findByEmail("username"))
                .thenReturn(Optional.of(user));

        when(request.getSession())
                .thenReturn(session);

        controller.viewHomepage(model, request);

        verify(houseService).viewHouseInHome();
        verify(roomService).viewRoomInHome();

        verify(model).addAttribute("houses", any());
        verify(model).addAttribute("rooms", any());

        //Verify lưu user vào session
        verify(session).setAttribute("user", user);

        //Sử dụng ArgumentCaptor kiểm tra giá trị lưu vào session
        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(session).setAttribute(eq("user"), userCaptor.capture());
        assertEquals(user, userCaptor.getValue());
    }
}