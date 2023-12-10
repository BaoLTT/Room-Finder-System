package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.repository.FeedbackRepository;

import com.roomfindingsystem.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    //test getFeedbackByHouseId
    @Test
    void testGetFeedbackByHouseIdWithValidInput() {
        // Arrange
        int houseId = 1;
        List<Boolean> status = Arrays.asList(true, false);

        // Mock data for FeedbackDto
        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseId(houseId, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseId(houseId, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList.get(0).getFeedbackId(), result.get(0).getFeedbackId());

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByHouseId(houseId, status);
    }

    @Test
    void testGetFeedbackByHouseIdWithEmptyResult() {
        // Arrange
        int houseId = 2; // Assuming houseId with no feedback data
        List<Boolean> status = Arrays.asList(true, false); // Doesn't matter in this case

        when(feedbackRepository.findFeedbackDtosByHouseId(houseId, status)).thenReturn(Collections.emptyList());

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseId(houseId, status);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByHouseId(houseId, status);
    }

    //test getFeedbackByHouseIdAndStar
    @Test
    void testGetFeedbackByHouseIdAndStarWithValidInput() {
        // Arrange
        int houseId = 1;
        int star = 5;
        List<Boolean> status = Arrays.asList(true, false); // Doesn't matter in this case

        // Mock data for FeedbackDto
        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(5);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByHouseIdAndStar(houseId, star, status);
    }

    @Test
    void testGetFeedbackByHouseIdAndStarWithEmptyResult() {
        // Arrange
        int houseId = 2; // Assuming houseId with no feedback data
        int star = 3; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(true, false); // Doesn't matter in this case

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(Collections.emptyList());

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByHouseIdAndStar(houseId, star, status);
    }

    //test GetFeedbackEntityByUid
    @Test
    void testGetFeedbackEntityByUidWithValidInput() {
        // Arrange
        int houseId = 1;
        int memberId = 1;

        // Mock data for FeedbackEntity
        FeedbackEntity feedbackEntity = new FeedbackEntity(); feedbackEntity.setFeedbackId(1);
        List<FeedbackEntity> feedbackEntityList = List.of(feedbackEntity);

        when(feedbackRepository.getFeedbackEntityByUid(houseId, memberId)).thenReturn(feedbackEntityList);

        // Act
        List<FeedbackEntity> result = feedbackService.getFeedbackEntityByUid(houseId, memberId);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackEntityList.get(0).getFeedbackId(), result.get(0).getFeedbackId());

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).getFeedbackEntityByUid(houseId, memberId);
    }

    @Test
    void testGetFeedbackEntityByUidWithEmptyResult() {
        // Arrange
        int houseId = 2; // Assuming houseId with no feedback data
        int memberId = 3; // Assuming memberId with no feedback data

        when(feedbackRepository.getFeedbackEntityByUid(houseId, memberId)).thenReturn(Collections.emptyList());

        // Act
        List<FeedbackEntity> result = feedbackService.getFeedbackEntityByUid(houseId, memberId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).getFeedbackEntityByUid(houseId, memberId);
    }

    //test
    @Test
    void testGetListFeedbackForLandLordWithValidData() {
        // Arrange
        int validCreatedBy = 1;
        int size = 2;
        FeedbackListAdminDto feedbackListAdminDto = new FeedbackListAdminDto();
        FeedbackListAdminDto feedbackListAdminDto1 = new FeedbackListAdminDto();
        List<FeedbackListAdminDto> list = List.of(feedbackListAdminDto, feedbackListAdminDto1);

        when(feedbackRepository.getFeedbackListForLandLord(validCreatedBy)).thenReturn(list);

        // Act
        List<FeedbackListAdminDto> feedbackList = feedbackService.getListFeedbackForLandLord(validCreatedBy);

        // Assert
        assertNotNull(feedbackList);
        assertEquals(size, feedbackList.size());
    }

    @Test
    void testGetListFeedbackForLandLordWithNoData() {
        // Arrange
        int nonExistentCreatedBy = 2;
        when(feedbackRepository.getFeedbackListForLandLord(nonExistentCreatedBy)).thenReturn(Collections.emptyList());

        // Act
        List<FeedbackListAdminDto> feedbackList = feedbackService.getListFeedbackForLandLord(nonExistentCreatedBy);

        // Assert
        assertNotNull(feedbackList);
        assertTrue(feedbackList.isEmpty());
    }

    //test
    @Test
    void testGetListFeedback() {
        // Arrange
        List<FeedbackListAdminDto> expectedFeedbackList = Arrays.asList(
                new FeedbackListAdminDto(/* your data here */),
                new FeedbackListAdminDto(/* your data here */)
                // Add more sample FeedbackListAdminDto objects as needed
        );

        // Mock the behavior of feedbackRepository.getFeedbackListForAdmin()
        when(feedbackRepository.getFeedbackListForAdmin()).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackListAdminDto> result = feedbackService.getListFeedback();

        // Assert
        assertEquals(expectedFeedbackList, result);
        // Add more assertions as needed
    }
}
