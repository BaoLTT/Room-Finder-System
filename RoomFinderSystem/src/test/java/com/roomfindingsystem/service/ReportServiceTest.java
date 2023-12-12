package com.roomfindingsystem.service;

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

}
