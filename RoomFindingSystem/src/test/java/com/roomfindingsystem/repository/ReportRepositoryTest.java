package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ReportEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReportRepositoryTest {
    @Autowired
    private ReportRepository reportRepository;

    //test GetReportEntityByUid
    @Test
    void testGetReportEntityByUidWithValidInput() {
        // Arrange
        int houseId = 1;
        int userId = 2;

        // Mock data for ReportEntity
        ReportEntity reportEntity = new ReportEntity(); reportEntity.setReportid(3);
        List<ReportEntity> reportEntityList = List.of(reportEntity);


        // Act
        List<ReportEntity> result = reportRepository.getReportEntityByUid(houseId, userId);

        // Assert
        assertNotNull(result);
        assertEquals(reportEntityList.get(0).getReportid(), result.get(0).getReportid());

    }

    @Test
    void testGetReportEntityByUidWithEmptyResult() {
        // Arrange
        int houseId = 2; // Assuming houseId with no report data
        int userId = 3; // Assuming userId with no report data


        // Act
        List<ReportEntity> result = reportRepository.getReportEntityByUid(houseId, userId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }
}
