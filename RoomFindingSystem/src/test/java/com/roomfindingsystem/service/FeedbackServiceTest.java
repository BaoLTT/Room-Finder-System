package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackDtoAdmin;
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
        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(5);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);
    }

    @Test
    void testGetFeedbackByHouseIdAndStar1() {
        // Arrange
        int houseId = 1; // Assuming houseId with no feedback data
        int star = 5; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(true); // Doesn't matter in this case

        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(5);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);}

    @Test
    void testGetFeedbackByHouseIdAndStar2() {
        // Arrange
        int houseId = 1; // Assuming houseId with no feedback data
        int star = 5; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(false); // Doesn't matter in this case

        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(5);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);}

    @Test
    void testGetFeedbackByHouseIdAndStar3() {
        // Arrange
        int houseId = 1; // Assuming houseId with no feedback data
        int star = 5; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(true,false); // Doesn't matter in this case

        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(5);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);}

    @Test
    void testGetFeedbackByHouseIdAndStar4() {
        // Arrange
        int houseId = 1; // Assuming houseId with no feedback data
        int star = 1; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(true); // Doesn't matter in this case

        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(1);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);}

    @Test
    void testGetFeedbackByHouseIdAndStar5() {
        // Arrange
        int houseId = 1; // Assuming houseId with no feedback data
        int star = 1; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(false); // Doesn't matter in this case

        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(1);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);}

    @Test
    void testGetFeedbackByHouseIdAndStar6() {
        // Arrange
        int houseId = 1; // Assuming houseId with no feedback data
        int star = 1; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(true,false); // Doesn't matter in this case

        FeedbackDto feedbackDto = new FeedbackDto(); feedbackDto.setFeedbackId(1); feedbackDto.setStar(1);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);

        when(feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status)).thenReturn(feedbackDtoList);

        // Act
        List<FeedbackDto> result = feedbackService.getFeedbackByHouseIdAndStar(houseId, star, status);

        // Assert
        assertNotNull(result);
        assertEquals(feedbackDtoList, result);}

    @Test
    void testGetFeedbackByHouseIdAndStar7() {
        // Arrange
        int houseId = -1; // Assuming houseId with no feedback data
        int star = 5; // Assuming star with no feedback data
        List<Boolean> status = Arrays.asList(true); // Doesn't matter in this case

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
    void testGetFeedbackEntityByUi1() {
        // Arrange
        int houseId = 1;
        int memberId = 0;

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
    void testGetFeedbackEntityByUi2() {
        // Arrange
        int houseId = 1;
        int memberId = -1;

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
    void testGetFeedbackEntityByUi3() {
        // Arrange
        int houseId = 0;
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
    void testGetFeedbackEntityByUi4() {
        // Arrange
        int houseId = 0;
        int memberId = 0;

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
    void testGetFeedbackEntityByUi5() {
        // Arrange
        int houseId = 0;
        int memberId = -1;

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
    void testGetFeedbackEntityByUi6() {
        // Arrange
        int houseId = -1;
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
    void testGetFeedbackEntityByUi8() {
        // Arrange
        int houseId = -1;
        int memberId = 0;

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
    void testGetFeedbackEntityByUi9() {
        // Arrange
        int houseId = -1;
        int memberId = -1;

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
                new FeedbackListAdminDto(),
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

    @Test
    void testGetFeedback() {
        List<Boolean> status = Arrays.asList(false);
        // Arrange
        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin()
                // Add more sample FeedbackListAdminDto objects as needed
        );

        // Mock the behavior of feedbackRepository.getFeedbackListForAdmin()
        when(feedbackRepository.findFeedbackDtos(status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> result = feedbackService.getFeedback(status);

        // Assert
        assertEquals(expectedFeedbackList, result);
        // Add more assertions as needed
    }

    @Test
    void testGetFeedback1() {
        List<Boolean> status = Arrays.asList(true);
        // Arrange
        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin()
                // Add more sample FeedbackListAdminDto objects as needed
        );

        // Mock the behavior of feedbackRepository.getFeedbackListForAdmin()
        when(feedbackRepository.findFeedbackDtos(status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> result = feedbackService.getFeedback(status);

        // Assert
        assertEquals(expectedFeedbackList, result);
        // Add more assertions as needed
    }

    @Test
    void testGetFeedback2() {
        List<Boolean> status = Arrays.asList(false,true);
        // Arrange
        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin()
                // Add more sample FeedbackListAdminDto objects as needed
        );

        // Mock the behavior of feedbackRepository.getFeedbackListForAdmin()
        when(feedbackRepository.findFeedbackDtos(status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> result = feedbackService.getFeedback(status);

        // Assert
        assertEquals(expectedFeedbackList, result);
        // Add more assertions as needed
    }

    //getFeedbackByStar
    @Test
    void testGetFeedbackByStar() {
        // Arrange
        int star = 5;
        List<Boolean> status = Arrays.asList(true);

        // Mô phỏng dữ liệu trả về từ repository
        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin(/* dữ liệu phản hồi 1 */),
                new FeedbackDtoAdmin(/* dữ liệu phản hồi 2 */)
                // Thêm các phần tử khác nếu cần
        );
        when(feedbackRepository.findFeedbackDtosByStar(star, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> actualFeedbackList = feedbackService.getFeedbackByStar(star, status);

        // Assert
        // Kiểm tra xem kết quả thực tế có trùng với kết quả mong đợi không
        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByStar(star, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void testGetFeedbackByStar1() {
        // Arrange
        int star = 5;
        List<Boolean> status = Arrays.asList(false);


        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin(),
                new FeedbackDtoAdmin()
        );
        when(feedbackRepository.findFeedbackDtosByStar(star, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> actualFeedbackList = feedbackService.getFeedbackByStar(star, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByStar(star, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void testGetFeedbackByStar2() {
        // Arrange
        int star = 5;
        List<Boolean> status = Arrays.asList(true, false);


        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin(),
                new FeedbackDtoAdmin()
        );
        when(feedbackRepository.findFeedbackDtosByStar(star, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> actualFeedbackList = feedbackService.getFeedbackByStar(star, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByStar(star, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void testGetFeedbackByStar3() {
        // Arrange
        int star = 3 ;
        List<Boolean> status = Arrays.asList(true);


        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin(),
                new FeedbackDtoAdmin()
        );
        when(feedbackRepository.findFeedbackDtosByStar(star, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> actualFeedbackList = feedbackService.getFeedbackByStar(star, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByStar(star, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void testGetFeedbackByStar4() {
        // Arrange
        int star = 3 ;
        List<Boolean> status = Arrays.asList(false);


        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin(),
                new FeedbackDtoAdmin()
        );
        when(feedbackRepository.findFeedbackDtosByStar(star, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> actualFeedbackList = feedbackService.getFeedbackByStar(star, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByStar(star, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void testGetFeedbackByStar5() {
        // Arrange
        int star = 3 ;
        List<Boolean> status = Arrays.asList(true,false);


        List<FeedbackDtoAdmin> expectedFeedbackList = Arrays.asList(
                new FeedbackDtoAdmin(),
                new FeedbackDtoAdmin()
        );
        when(feedbackRepository.findFeedbackDtosByStar(star, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDtoAdmin> actualFeedbackList = feedbackService.getFeedbackByStar(star, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByStar(star, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    //saveFeedback
    @Test
    void testSaveFeedback() {
        // Arrange
        FeedbackEntity inputFeedbackEntity = new FeedbackEntity(); inputFeedbackEntity.setFeedbackId(1);


        FeedbackEntity expectedSavedFeedbackEntity = new FeedbackEntity(); expectedSavedFeedbackEntity.setFeedbackId(1);
        when(feedbackRepository.save(inputFeedbackEntity)).thenReturn(expectedSavedFeedbackEntity);

        // Act
        FeedbackEntity actualSavedFeedbackEntity = feedbackService.save(inputFeedbackEntity);

        // Assert
        assertEquals(expectedSavedFeedbackEntity, actualSavedFeedbackEntity);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).save(inputFeedbackEntity);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void testSaveInvalidFeedback() {
        // Arrange
        FeedbackEntity invalidFeedbackEntity = new FeedbackEntity(); invalidFeedbackEntity = null;

        // Act
        FeedbackEntity savedFeedbackEntity = feedbackService.save(invalidFeedbackEntity);

        // Assert
        assertNull(savedFeedbackEntity);

        // Verify that the repository method was not called
        verify(feedbackRepository, never()).save(any(FeedbackEntity.class));

    }

    //test getFeedbackByHouseId
    @Test
    void getFeedbackByHouseId(){
        int houseId = 1 ;
        List<Boolean> status = Arrays.asList(true);


        List<FeedbackDto> expectedFeedbackList = Arrays.asList(
                new FeedbackDto(), new FeedbackDto()
        );
        when(feedbackRepository.findFeedbackDtosByHouseId(houseId, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDto> actualFeedbackList = feedbackService.getFeedbackByHouseId(houseId, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByHouseId(houseId, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void getFeedbackByHouseId1(){
        int houseId = 1 ;
        List<Boolean> status = Arrays.asList(false);


        List<FeedbackDto> expectedFeedbackList = Arrays.asList(
                new FeedbackDto(), new FeedbackDto()
        );
        when(feedbackRepository.findFeedbackDtosByHouseId(houseId, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDto> actualFeedbackList = feedbackService.getFeedbackByHouseId(houseId, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByHouseId(houseId, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void getFeedbackByHouseId2(){
        int houseId = 1 ;
        List<Boolean> status = Arrays.asList(true,false);


        List<FeedbackDto> expectedFeedbackList = Arrays.asList(
                new FeedbackDto(), new FeedbackDto()
        );
        when(feedbackRepository.findFeedbackDtosByHouseId(houseId, status)).thenReturn(expectedFeedbackList);

        // Act
        List<FeedbackDto> actualFeedbackList = feedbackService.getFeedbackByHouseId(houseId, status);

        // Assert

        assertEquals(expectedFeedbackList, actualFeedbackList);

        // Verify that the repository method was called with the correct parameters
        verify(feedbackRepository, times(1)).findFeedbackDtosByHouseId(houseId, status);

        // Verify that no other interactions with feedbackRepository occurred
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void getFeedbackByHouseId3(){
        // Arrange
        int invalidHouseId = -1;
        List<Boolean> status = Collections.singletonList(true);
        // Act
        List<FeedbackDto> resultFeedbackList = feedbackService.getFeedbackByHouseId(invalidHouseId, status);

        // Assert
        // Kiểm tra xem kết quả trả về có phải là null hay không
        assertTrue(resultFeedbackList.isEmpty());


    }

    @Test
    void getFeedbackByHouseId4(){
        // Arrange
        int invalidHouseId = -1;
        List<Boolean> status = Collections.singletonList(false);
        // Act
        List<FeedbackDto> resultFeedbackList = feedbackService.getFeedbackByHouseId(invalidHouseId, status);

        // Assert
        // Kiểm tra xem kết quả trả về có phải là null hay không
        assertTrue(resultFeedbackList.isEmpty());


    }

    @Test
    void getFeedbackByHouseId5(){
        // Arrange
        int invalidHouseId = -1;
        List<Boolean> status = Arrays.asList(true,false);
        // Act
        List<FeedbackDto> resultFeedbackList = feedbackService.getFeedbackByHouseId(invalidHouseId, status);

        // Assert
        // Kiểm tra xem kết quả trả về có phải là null hay không
        assertTrue(resultFeedbackList.isEmpty());


    }

    @Test
    void getFeedbackByHouseId6(){
        // Arrange
        int invalidHouseId = 0;
        List<Boolean> status = Collections.singletonList(true);
        // Act
        List<FeedbackDto> resultFeedbackList = feedbackService.getFeedbackByHouseId(invalidHouseId, status);

        // Assert
        // Kiểm tra xem kết quả trả về có phải là null hay không
        assertTrue(resultFeedbackList.isEmpty());


    }

    @Test
    void getFeedbackByHouseId7(){
        // Arrange
        int invalidHouseId = 0;
        List<Boolean> status = Collections.singletonList(false);
        // Act
        List<FeedbackDto> resultFeedbackList = feedbackService.getFeedbackByHouseId(invalidHouseId, status);

        // Assert
        // Kiểm tra xem kết quả trả về có phải là null hay không
        assertTrue(resultFeedbackList.isEmpty());


    }



}
