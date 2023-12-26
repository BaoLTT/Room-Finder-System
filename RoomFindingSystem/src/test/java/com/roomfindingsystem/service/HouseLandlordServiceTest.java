package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.repository.HouseImageRepository;
import com.roomfindingsystem.repository.HouseLandlordRepository;
import com.roomfindingsystem.repository.HouseManagerRepository;
import com.roomfindingsystem.service.impl.HouseLandlordServiceImpl;
import com.roomfindingsystem.service.impl.HouseManagerServiceImpl;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseLandlordServiceTest {
    @InjectMocks
    HouseLandlordServiceImpl houseLandlordService;

    @InjectMocks
    private HouseManagerServiceImpl houseManagerService;

    @Mock
    private HouseManagerRepository houseManagerRepository;

    @Mock
    HouseLandlordRepository houseLandlordRepository;
    @Mock
    HouseImageRepository houseImageRepository;
    @Test
    void findHouseByUser() {
        // Mocking data
        int userId = 1;

        // Mocking repository behavior
        List<Tuple> mockTuples = new ArrayList<>();
        // Add tuples to mockTuples if needed

        when(houseLandlordRepository.findHouseByUser(userId))
                .thenReturn(mockTuples);

        // Call the method you want to test
        List<HouseLandlordVo> result = houseLandlordService.findHouseByUser(userId);

        // Verify that findHouseByUser was called with the correct argument
        verify(houseLandlordRepository).findHouseByUser(userId);

    }

    @Test
    void findHouseByUser100() {
        // Mocking data
        int userId = 100;

        // Mocking repository behavior
        List<Tuple> mockTuples = new ArrayList<>();
        // Add tuples to mockTuples if needed

        when(houseLandlordRepository.findHouseByUser(userId))
                .thenReturn(Collections.emptyList());

        // Call the method you want to test
        List<HouseLandlordVo> result = houseLandlordService.findHouseByUser(userId);

        // Verify that findHouseByUser was called with the correct argument
        verify(houseLandlordRepository).findHouseByUser(userId);

        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    @Test
    void findHouseByUser0() {
        // Mocking data
        int userId = 0;

        // Mocking repository behavior
        List<Tuple> mockTuples = new ArrayList<>();
        // Add tuples to mockTuples if needed

        when(houseLandlordRepository.findHouseByUser(userId))
                .thenReturn(Collections.emptyList());

        // Call the method you want to test
        List<HouseLandlordVo> result = houseLandlordService.findHouseByUser(userId);

        // Verify that findHouseByUser was called with the correct argument
        verify(houseLandlordRepository).findHouseByUser(userId);

        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    @Test
    void findHouseByUserNegative() {
        // Mocking data
        int userId = -1;

        // Mocking repository behavior
        List<Tuple> mockTuples = new ArrayList<>();
        // Add tuples to mockTuples if needed

        when(houseLandlordRepository.findHouseByUser(userId))
                .thenReturn(Collections.emptyList());

        // Call the method you want to test
        List<HouseLandlordVo> result = houseLandlordService.findHouseByUser(userId);

        // Verify that findHouseByUser was called with the correct argument
        verify(houseLandlordRepository).findHouseByUser(userId);

        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    @Test
    void getAllHouse() {
        // Mocking repository behavior
        List<Tuple> mockTuples = new ArrayList<>();
        // Add tuples to mockTuples if needed

        when(houseLandlordRepository.getAllHouse())
                .thenReturn(mockTuples);

        // Call the method you want to test
        List<HouseLandlordVo> result = houseLandlordService.getAllHouse();

        // Verify that getAllHouse was called once
        verify(houseLandlordRepository).getAllHouse();
    }

    @Test
    void deleteImageById() {
        // Mocking data
        int imageId = 1;

        // Call the method you want to test
        houseLandlordService.deleteImageById(imageId);

        // Verify that deleteById was called with the correct argument
        verify(houseImageRepository).deleteById(imageId);
    }
    @Test
    void deleteImageById1000() {
        // Mocking data
        int imageId = 1000;

        // Call the method you want to test
        doThrow(new EmptyResultDataAccessException(1)).when(houseImageRepository).deleteById(imageId);

        // Call the method you want to test
        assertThrows(EmptyResultDataAccessException.class, () -> houseLandlordService.deleteImageById(imageId));

        // Verify that deleteById was called with the correct argument
        verify(houseImageRepository).deleteById(imageId);
    }

    @Test
    void deleteImageById0() {
        // Mocking data
        int imageId = 0;

        // Call the method you want to test
        doThrow(new EmptyResultDataAccessException(1)).when(houseImageRepository).deleteById(imageId);

        // Call the method you want to test
        assertThrows(EmptyResultDataAccessException.class, () -> houseLandlordService.deleteImageById(imageId));

        // Verify that deleteById was called with the correct argument
        verify(houseImageRepository).deleteById(imageId);
    }

    @Test
    void deleteImageByIdNega() {
        // Mocking data
        int imageId = -1;

        // Call the method you want to test
        doThrow(new EmptyResultDataAccessException(1)).when(houseImageRepository).deleteById(imageId);

        // Call the method you want to test
        assertThrows(EmptyResultDataAccessException.class, () -> houseLandlordService.deleteImageById(imageId));

        // Verify that deleteById was called with the correct argument
        verify(houseImageRepository).deleteById(imageId);
    }

    @Test
    void findHouseById1() {
        // Test data
        int houseId = 1; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = new HouseManagerTypeVo(); // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertEquals(expectedHouse, result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void findHouseById200() {
        // Test data
        int houseId = 200; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = null; // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertNull(result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void findHouseById0() {
        // Test data
        int houseId = 0; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = null; // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertNull(result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void findHouseByIdNegative() {
        // Test data
        int houseId = -1; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = null; // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertNull(result); // Assuming you are comparing the result with the expectedHouse
    }

    //getListHouseLandlordVo
    @Test
    void testGetListHouseLandlordVo_WithValidData() {
        // Arrange
        List<Tuple> validTuples = new ArrayList<>(); // You can use your method to create valid tuples


    }

    @Test
    void testGetListHouseLandlordVo_WithInvalidData() {
        // Arrange
        List<Tuple> invalidTuples = null; // or Collections.emptyList() for an empty list

        // Mock the behavior of your repository to return the list of tuples
//        when(houseLandlordRepository.getAllHouse()).thenReturn(invalidTuples);

        // Act
//        List<HouseLandlordVo> result = houseLandlordService.getListHouseLandlordVo(invalidTuples);


//        assertNotNull(result);
//        assertTrue(result.isEmpty());

    }

    @Test
    public void testGetHouseLandlordVo_WithInvalidTuple() {
        // Arrange
        int houseId = 1;
//        when(houseLandlordRepository.findHouseByID(houseId)).thenReturn(null); // Simulating an invalid Tuple
//
//        // Act
//        HouseLandlordVo result = houseLandlordService.findHouseByID(houseId);
//
//        // Assert
//        assertNull(result); // Adjust the assertion based on your expected behavior
    }

    @Test
    public void testGetHouseLandlordVo_WithValidTuple() {
        // Arrange
        int houseId = 1;
        Tuple tuple = null;
        // Set up the mock Tuple
//        when(tuple.get("HouseID", Integer.class)).thenReturn(123); // Adjust values based on your needs
//        when(tuple.get("status", Integer.class)).thenReturn(1);
//        // ... continue setting up other values ...
//
//        // Act
//        HouseLandlordVo result = houseLandlordService.getHouseLandlordVo(tuple, houseId);
//
//        // Assert
//        assertNotNull(result);
//
//        assertEquals(123, result.getHouseID()); // Adjust based on your expected values
//        assertEquals(1, result.getStatus());

    }
}