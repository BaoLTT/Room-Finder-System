package com.roomfindingsystem.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.HouseDto;
import com.roomfindingsystem.dto.HouseImageLink;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.service.FeedbackService;
import com.roomfindingsystem.service.HouseService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HouseControllerTest {

    @Autowired
    private HouseService houseService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private HouseController houseController;

    @Test
    public void testGetAllHouse() throws Exception {
        // Arrange
        int houseId = 1;
        int star = 2;
        HttpServletRequest request = mock(HttpServletRequest.class);
        ModelMap model = mock(ModelMap.class);

        List<HouseDto> houseDtoList = houseService.getHouseDetail(houseId);

        List<ServiceDto> serviceDtoList = houseService.getServiceById(houseId);

        List<HouseImageLink> houseImageList = houseService.getImageById(houseId);

        List<FeedbackDto> feedbackDtoList = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, Arrays.asList(true));

        // Act
        String viewName = houseController.getAllHouse(1, houseId, star, model, request);

        // Assert
        assertEquals("housedetail", viewName);
        verify(model).addAttribute("HousesEntity", houseDtoList);
        verify(model).addAttribute("HouseService", serviceDtoList);
        verify(model).addAttribute("HousesImages", houseImageList);
        verify(model).addAttribute("feedbacks", feedbackDtoList);
        verify(model).addAttribute("star", star);
    }



}
