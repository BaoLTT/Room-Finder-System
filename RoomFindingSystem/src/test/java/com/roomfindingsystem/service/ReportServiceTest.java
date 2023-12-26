package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.ReportDto;
import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.repository.ReportRepository;
import com.roomfindingsystem.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    //test GetReportEntityByUid
    @Test
    void testGetReportEntityByUidWithValidInput() {
        // Arrange
        int houseId = 1;
        int userId = 1;

        // Mock data for ReportEntity
        ReportEntity reportEntity = new ReportEntity(); reportEntity.setReportid(2);
        List<ReportEntity> reportEntityList = List.of(reportEntity);

        when(reportRepository.getReportEntityByUid(houseId, userId)).thenReturn(reportEntityList);

        // Act
        List<ReportEntity> result = reportService.getReportEntityByUid(houseId, userId);

        // Assert
        assertNotNull(result);
        assertEquals(reportEntityList.get(0).getReportid(), result.get(0).getReportid());

        // Verify that the repository method was called with the correct parameters
        verify(reportRepository, times(1)).getReportEntityByUid(houseId, userId);
    }

    @Test
    void testGetReportEntityByUidWithEmptyResult() {
        // Arrange
        int houseId = 2; // Assuming houseId with no report data
        int userId = 3; // Assuming userId with no report data

        when(reportRepository.getReportEntityByUid(houseId, userId)).thenReturn(Collections.emptyList());

        // Act
        List<ReportEntity> result = reportService.getReportEntityByUid(houseId, userId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(reportRepository, times(1)).getReportEntityByUid(houseId, userId);
    }

    @Test
    void testSaveReport_ValidEntity() {
        // Mock data
        ReportEntity validReportEntity = new ReportEntity(/* set valid values */);

        // Stub the behavior of the repository
        when(reportRepository.save(validReportEntity)).thenReturn(validReportEntity);

        // Call the method to test
        ReportEntity savedReport = reportService.save(validReportEntity);

        // Verify interactions and assertions
        assertNotNull(savedReport);
        assertEquals(validReportEntity, savedReport);

        // Verify that the repository method was called once with the expected entity
        verify(reportRepository, times(1)).save(validReportEntity);
    }

    @Test
    void testSaveReport_InValidEntity() {
        // Mock data
        ReportEntity invalidReportEntity = new ReportEntity();

        // Stub the behavior of the repository to throw an exception (simulate failure)
        when(reportRepository.save(invalidReportEntity)).thenReturn(null);

        assertEquals(null, reportService.save(invalidReportEntity));

        // Verify that the repository method was called once with the expected entity
        verify(reportRepository, times(1)).save(invalidReportEntity);
    }

    @Test
    void testGetEmailForReply_WithValidHouseName() {
        // Mock data
        String validHouseName = "Tony";
        ReportDto expectedReportDto = new ReportDto(/* set expected values */);

        // Stub the behavior of the repository to return an Optional containing the expected ReportDto
        when(reportRepository.getEmailForRepLy(validHouseName)).thenReturn(Optional.of(expectedReportDto));

        // Call the method to test
        Optional<ReportDto> result = reportService.getEmailForReply(validHouseName);

        // Verify that the repository method was called once with the expected houseName
        verify(reportRepository, times(1)).getEmailForRepLy(validHouseName);

        // Assert that the result contains the expected ReportDto
        assertTrue(result.isPresent());
        assertEquals(expectedReportDto, result.get());
    }

    @Test
    void testGetEmailForReply_WithEmptyHouseName() {
        // Mock data
        String emptyHouseName = "";

        // Stub the behavior of the repository to return null
        when(reportRepository.getEmailForRepLy(emptyHouseName)).thenReturn(null);

        // Call the method to test
        Optional<ReportDto> result = reportService.getEmailForReply(emptyHouseName);

        // Verify that the repository method was called once with the expected houseName
        verify(reportRepository, times(1)).getEmailForRepLy(emptyHouseName);

//        // Assert that the result is null
//        assertTrue(result.isEmpty());
    }

    @Test
    void testGetEmailForReply_WithNullHouseName() {
        // Mock data
        String nullHouseName = null;

        // Stub the behavior of the repository to return null
        when(reportRepository.getEmailForRepLy(nullHouseName)).thenReturn(null);

        // Call the method to test
        Optional<ReportDto> result = reportService.getEmailForReply(nullHouseName);

        // Verify that the repository method was called once with the expected houseName
        verify(reportRepository, times(1)).getEmailForRepLy(nullHouseName);

        // Assert that the result is null
//        assertFalse(result.isPresent());
    }
}
