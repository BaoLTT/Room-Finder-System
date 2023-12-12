package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.impl.FeedbackServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackControllerTest {
    @InjectMocks
    private FeebackController feebackController;

    @Mock
    private FeedbackServiceImpl feedbackService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFeedbackListForLandLordWithValidData() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession())
                .thenReturn(session);
        Model model = mock(Model.class);
        when(session.getAttribute("user")).thenReturn(mock(UserEntity.class));

        FeedbackListAdminDto feedbackListAdminDto = new FeedbackListAdminDto();
        FeedbackListAdminDto feedbackListAdminDto1 = new FeedbackListAdminDto();
        List<FeedbackListAdminDto> list = List.of(feedbackListAdminDto, feedbackListAdminDto1);


        // Act
        String resultView = feebackController.getFeebackListForLandLord(model, session, request);

        // Assert
        assertEquals("feedback-list-landlord", resultView);
        verify(model).addAttribute("request", request);
        verify(session).getAttribute("user");
    }

    @Test
    void testGetFeedbackListForLandLordWithNoData() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession())
                .thenReturn(session);
        Model model = mock(Model.class);
        UserEntity user = new UserEntity();
        user.setUserId(2);
        when(session.getAttribute("user")).thenReturn(user);

        when(feedbackService.getListFeedbackForLandLord(user.getUserId())).thenReturn(Collections.emptyList());

        // Act
        String resultView = feebackController.getFeebackListForLandLord(model, session, request);

        // Assert
        assertEquals("feedback-list-landlord", resultView);
        verify(model).addAttribute("feedbackList", Collections.emptyList());
        verify(model).addAttribute("request", request);
        verify(session).getAttribute("user");
    }
}
