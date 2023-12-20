package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.FeedbackEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FeedbackRepositoryTest {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Test
    void testGetFeedbackByHouseIdWithValidInput() {
        // Arrange
        int houseId = 1;
        List<Boolean> status = Arrays.asList(true, false);

        // Mock data for FeedbackDto
        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);


        // Act
        List<FeedbackDto> result = feedbackRepository.findFeedbackDtosByHouseId(houseId, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList.get(0).getFeedbackId(), result.get(0).getFeedbackId());

    }

    @Test
    void testGetFeedbackByHouseIdWithEmptyResult() {
        // Arrange
        int houseId = 100; // Assuming houseId with no feedback data
        List<Boolean> status = Arrays.asList(true, false); // Doesn't matter in this case

        // Act
        List<FeedbackDto> result = feedbackRepository.findFeedbackDtosByHouseId(houseId,status);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    //test getFeedbackByHouseIdAndStar
    @Test
    void testGetFeedbackByHouseIdAndStarWithValidInput() {
        // Arrange
        int houseId = 1;
        int star = 5;
        List<Boolean> status = Arrays.asList(true, false); // Doesn't matter in this case

        // Mock data for FeedbackDto
        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(2); feedbackDto.setStar(5);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        // Act
        List<FeedbackDto> result = feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId,star,status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList.get(0).getFeedbackId(), result.get(0).getFeedbackId());

    }
    @Test
    void testGetFeedbackByHouseIdAndStarWithEmptyResult() {
        // Arrange
        int houseId = 2; // Assuming houseId with no feedback data
        int star = 1; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(true, false); // Doesn't matter in this case

        // Act
        List<FeedbackDto> result = feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());


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

        // Act
        List<FeedbackEntity> result = feedbackRepository.getFeedbackEntityByUid(houseId, memberId);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackEntityList.get(0).getFeedbackId(), result.get(0).getFeedbackId());

    }

    @Test
    void testGetFeedbackEntityByUidWithEmptyResult() {
        // Arrange
        int houseId = 2; // Assuming houseId with no feedback data
        int memberId = 3; // Assuming memberId with no feedback data


        // Act
        List<FeedbackEntity> result = feedbackRepository.getFeedbackEntityByUid(houseId, memberId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }


    //
    @Test
    void testGetListFeedbackForLandLordWithValidData() {
        // Arrange: Sử dụng cơ sở dữ liệu nhúng hoặc thực tế để tạo dữ liệu mẫu
        int validCreatedBy = 1;
        // Act
        List<FeedbackListAdminDto> feedbackList = feedbackRepository.getFeedbackListForLandLord(validCreatedBy);

        // Assert
        assertNotNull(feedbackList);
        assertFalse(feedbackList.isEmpty());
        // Kiểm tra các điều kiện mong muốn cho dữ liệu
    }

    @Test
    void testGetListFeedbackForLandLordWithNoData() {
        // Arrange: Sử dụng cơ sở dữ liệu nhúng hoặc thực tế để tạo dữ liệu mẫu
        int validCreatedBy = 145;
        // Act
        List<FeedbackListAdminDto> feedbackList = feedbackRepository.getFeedbackListForLandLord(validCreatedBy);

        // Assert
        assertNotNull(feedbackList);
        assertTrue(feedbackList.isEmpty());
    }

    //test
    @Test
    void testGetListFeedback() {

        // Add more assertions as needed
    }


}
