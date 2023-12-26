package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.dto.ReportDto;
import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.repository.ReportRepository;
import com.roomfindingsystem.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
    public void testCountReports() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Set up the behavior for reportRepository.countReports
        when(reportRepository.countReports()).thenReturn(5); // Set the expected count value

        // Create an instance of the service under test
        reportService  = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        int result = reportService.countReports();

        // Check the result
        assertEquals(5, result); // Adjust the expected count value as needed

        // Verify that reportRepository.countReports was called
        verify(reportRepository, times(1)).countReports();
    }

    @Test
    public void testCountProcessingReports() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Set up the behavior for reportRepository.countReports
        when(reportRepository.countProcessingReports()).thenReturn(5); // Set the expected count value

        // Create an instance of the service under test
        reportService  = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        int result = reportService.countProcessingReports();

        // Check the result
        assertEquals(5, result); // Adjust the expected count value as needed

        // Verify that reportRepository.countReports was called
        verify(reportRepository, times(1)).countProcessingReports();
    }

    @Test
    public void testCountProcessedReports() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Set up the behavior for reportRepository.countReports
        when(reportRepository.countProcessedReports()).thenReturn(5); // Set the expected count value

        // Create an instance of the service under test
        reportService  = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        int result = reportService.countProcessedReports();

        // Check the result
        assertEquals(5, result); // Adjust the expected count value as needed

        // Verify that reportRepository.countReports was called
        verify(reportRepository, times(1)).countProcessedReports();
    }

    @Test
    public void testGetReportEntityByUid1() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create some dummy data for the expected result
        ReportEntity report1 = new ReportEntity();
        report1.setReportid(1);

        ReportEntity report2 = new ReportEntity();
        report2.setReportid(2);

        List<ReportEntity> expectedReports = Arrays.asList(report1, report2);

        // Set up the behavior for reportRepository.getReportEntityByUid
        when(reportRepository.getReportEntityByUid(1, 5)).thenReturn(expectedReports);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        List<ReportEntity> result = reportService.getReportEntityByUid(1, 5);

        // Check the result
        assertEquals(expectedReports, result);

        // Verify that reportRepository.getReportEntityByUid was called with the correct parameters
        verify(reportRepository, times(1)).getReportEntityByUid(1, 5);
    }

    @Test
    public void testGetReportEntityByUid2() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create some dummy data for the expected result
        ReportEntity report1 = new ReportEntity();
        report1.setReportid(1);

        ReportEntity report2 = new ReportEntity();
        report2.setReportid(2);

        List<ReportEntity> expectedReports = Arrays.asList(report1, report2);

        // Set up the behavior for reportRepository.getReportEntityByUid
        when(reportRepository.getReportEntityByUid(-1, 5)).thenReturn(expectedReports);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        List<ReportEntity> result = reportService.getReportEntityByUid(-1, 5);

        // Check the result
        assertEquals(expectedReports, result);

        // Verify that reportRepository.getReportEntityByUid was called with the correct parameters
        verify(reportRepository, times(1)).getReportEntityByUid(-1, 5);
    }

    @Test
    public void testGetReportEntityByUid3() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create some dummy data for the expected result
        ReportEntity report1 = new ReportEntity();
        report1.setReportid(1);

        ReportEntity report2 = new ReportEntity();
        report2.setReportid(2);

        List<ReportEntity> expectedReports = Arrays.asList(report1, report2);

        // Set up the behavior for reportRepository.getReportEntityByUid
        when(reportRepository.getReportEntityByUid(1, -1)).thenReturn(expectedReports);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        List<ReportEntity> result = reportService.getReportEntityByUid(1, -1);

        // Check the result
        assertEquals(expectedReports, result);

        // Verify that reportRepository.getReportEntityByUid was called with the correct parameters
        verify(reportRepository, times(1)).getReportEntityByUid(1, -1);
    }

    @Test
    public void testGetReportEntityByUid4() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create some dummy data for the expected result
        ReportEntity report1 = new ReportEntity();
        report1.setReportid(1);

        ReportEntity report2 = new ReportEntity();
        report2.setReportid(2);

        List<ReportEntity> expectedReports = Arrays.asList(report1, report2);

        // Set up the behavior for reportRepository.getReportEntityByUid
        when(reportRepository.getReportEntityByUid(-1, -1)).thenReturn(expectedReports);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        List<ReportEntity> result = reportService.getReportEntityByUid(-1, -1);

        // Check the result
        assertEquals(expectedReports, result);

        // Verify that reportRepository.getReportEntityByUid was called with the correct parameters
        verify(reportRepository, times(1)).getReportEntityByUid(-1, -1);
    }

    @Test
    public void testDeleteByHouseIdAndMemberId() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        reportService.deleteByHouseIdAndMemberId(1, 1);

        // Verify that reportRepository.deleteByHouseIdAndMemberId was called with the correct parameters
        verify(reportRepository, times(1)).deleteByHouseIdAndMemberId(1, 1);
    }

    @Test
    public void testDeleteByHouseIdAndMemberId2() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        reportService.deleteByHouseIdAndMemberId(-1, 1);

        // Verify that reportRepository.deleteByHouseIdAndMemberId was called with the correct parameters
        verify(reportRepository, times(1)).deleteByHouseIdAndMemberId(-1, 1);
    }

    @Test
    public void testDeleteByHouseIdAndMemberId3() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        reportService.deleteByHouseIdAndMemberId(1, -1);

        // Verify that reportRepository.deleteByHouseIdAndMemberId was called with the correct parameters
        verify(reportRepository, times(1)).deleteByHouseIdAndMemberId(1, -1);
    }

    @Test
    public void testDeleteByHouseIdAndMemberId4() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        reportService.deleteByHouseIdAndMemberId(-1, -1);

        // Verify that reportRepository.deleteByHouseIdAndMemberId was called with the correct parameters
        verify(reportRepository, times(1)).deleteByHouseIdAndMemberId(-1, -1);
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

    @Test
    public void testGetAllReport() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create some dummy data for the expected result
        ReportListDto report1 = new ReportListDto();
        report1.setReportId(1);

        ReportListDto report2 = new ReportListDto();
        report2.setReportId(2);

        List<ReportListDto> expectedReports = Arrays.asList(report1, report2);

        // Set up the behavior for reportRepository.findAllReport
        when(reportRepository.findAllReport()).thenReturn(expectedReports);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Call the method to be tested
        List<ReportListDto> result = reportService.getAllReport();

        // Check the result
        assertEquals(expectedReports, result);

        // Verify that reportRepository.findAllReport was called
        verify(reportRepository, times(1)).findAllReport();
    }

    @Test
    public void testUpdateStatusProcessed() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = 1;

        // Set up the behavior for reportRepository.updateStatusReportProcessed
        when(reportRepository.updateStatusReportProcessed(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusProcessed(reportId);

        // Verify that reportRepository.updateStatusReportProcessed was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportProcessed(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateStatusProcessed2() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = -1;

        // Set up the behavior for reportRepository.updateStatusReportProcessed
        when(reportRepository.updateStatusReportProcessed(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusProcessed(reportId);

        // Verify that reportRepository.updateStatusReportProcessed was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportProcessed(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateStatusProcessed3() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = 0;

        // Set up the behavior for reportRepository.updateStatusReportProcessed
        when(reportRepository.updateStatusReportProcessed(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusProcessed(reportId);

        // Verify that reportRepository.updateStatusReportProcessed was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportProcessed(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateStatusHandle() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = 1;

        // Set up the behavior for reportRepository.updateStatusReportProcessed
        when(reportRepository.updateStatusReportHandle(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusHandle(reportId);

        // Verify that reportRepository.updateStatusReportProcessed was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportHandle(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateStatusHandle2() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = -1;

        // Set up the behavior for reportRepository.updateStatusReportProcessed
        when(reportRepository.updateStatusReportHandle(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusHandle(reportId);

        // Verify that reportRepository.updateStatusReportProcessed was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportHandle(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }
    @Test
    public void testUpdateStatusHandle3() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = 0;

        // Set up the behavior for reportRepository.updateStatusReportProcessed
        when(reportRepository.updateStatusReportHandle(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusHandle(reportId);

        // Verify that reportRepository.updateStatusReportProcessed was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportHandle(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateStatusWaiting() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = 1;

        // Set up the behavior for reportRepository.updateStatusReportWaiting
        when(reportRepository.updateStatusReportWaiting(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusWaiting(reportId);

        // Verify that reportRepository.updateStatusReportWaiting was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportWaiting(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateStatusWaiting2() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = -1;

        // Set up the behavior for reportRepository.updateStatusReportWaiting
        when(reportRepository.updateStatusReportWaiting(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusWaiting(reportId);

        // Verify that reportRepository.updateStatusReportWaiting was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportWaiting(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateStatusWaiting3() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the ID for testing
        int reportId = 0;

        // Set up the behavior for reportRepository.updateStatusReportWaiting
        when(reportRepository.updateStatusReportWaiting(reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateStatusWaiting(reportId);

        // Verify that reportRepository.updateStatusReportWaiting was called with the correct parameter
        verify(reportRepository, times(1)).updateStatusReportWaiting(reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateSolve() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the parameters for testing
        LocalDate solveDate = LocalDate.now();
        int reportId = 1;

        // Set up the behavior for reportRepository.updateProcessedDate
        when(reportRepository.updateProcessedDate(solveDate, reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateSolve(solveDate, reportId);

        // Verify that reportRepository.updateProcessedDate was called with the correct parameters
        verify(reportRepository, times(1)).updateProcessedDate(solveDate, reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }
    @Test
    public void testUpdateSolve2() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the parameters for testing
        LocalDate solveDate = LocalDate.now();
        int reportId = -1;

        // Set up the behavior for reportRepository.updateProcessedDate
        when(reportRepository.updateProcessedDate(solveDate, reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateSolve(solveDate, reportId);

        // Verify that reportRepository.updateProcessedDate was called with the correct parameters
        verify(reportRepository, times(1)).updateProcessedDate(solveDate, reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }
    @Test
    public void testUpdateSolve3() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the parameters for testing
        LocalDate solveDate = LocalDate.now();
        int reportId = 0;

        // Set up the behavior for reportRepository.updateProcessedDate
        when(reportRepository.updateProcessedDate(solveDate, reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateSolve(solveDate, reportId);

        // Verify that reportRepository.updateProcessedDate was called with the correct parameters
        verify(reportRepository, times(1)).updateProcessedDate(solveDate, reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateSolve4() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the parameters for testing
        LocalDate solveDate = LocalDate.of(2100, 2, 2);
        int reportId = 1;

        // Set up the behavior for reportRepository.updateProcessedDate
        when(reportRepository.updateProcessedDate(solveDate, reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateSolve(solveDate, reportId);

        // Verify that reportRepository.updateProcessedDate was called with the correct parameters
        verify(reportRepository, times(1)).updateProcessedDate(solveDate, reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateSolve5() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the parameters for testing
        LocalDate solveDate = LocalDate.of(2100, 2, 2);
        int reportId = -1;

        // Set up the behavior for reportRepository.updateProcessedDate
        when(reportRepository.updateProcessedDate(solveDate, reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateSolve(solveDate, reportId);

        // Verify that reportRepository.updateProcessedDate was called with the correct parameters
        verify(reportRepository, times(1)).updateProcessedDate(solveDate, reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }

    @Test
    public void testUpdateSolve6() {
        // Create a mock for the reportRepository
        ReportRepository reportRepository = mock(ReportRepository.class);

        // Create an instance of the service under test
        reportService = new ReportServiceImpl(reportRepository);

        // Define the parameters for testing
        LocalDate solveDate = LocalDate.of(2100, 2, 2);
        int reportId = 0;

        // Set up the behavior for reportRepository.updateProcessedDate
        when(reportRepository.updateProcessedDate(solveDate, reportId)).thenReturn(1); // Assuming 1 row is affected

        // Call the method to be tested
        int affectedRows = reportService.updateSolve(solveDate, reportId);

        // Verify that reportRepository.updateProcessedDate was called with the correct parameters
        verify(reportRepository, times(1)).updateProcessedDate(solveDate, reportId);

        // Check the result, assuming the repository method returns the number of affected rows
        assertEquals(1, affectedRows);
    }
}

