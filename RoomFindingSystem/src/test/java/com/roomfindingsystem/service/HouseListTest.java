package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.repository.HouseRepository;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.service.impl.HouseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class HouseListTest {
    @Mock
    HouseRepository houseRepository;

    @InjectMocks
    HouseServiceImpl houseService;



    @Test
    public void testCountHouse() {
        // Arrange
        int min1 = 2000000;
        int max1 = 4000000;
        int min2 = 4000000;
        int max2 = 6000000;
        String houseName = "TestHouse";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;

        // Mocking the behavior of houseRepository.countHouse
        when(houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService))
                .thenReturn(12);

        // Calling the service method
        int result = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);


        // Asserting the result
        assertEquals(11, result);
        verify(houseRepository).countHouse(min1, max1, min2, max2, houseName, type, service, countService);
    }
    @Test
    public void testCountHouse_NotExistName() {
        // Arrange
        int min1 = 2000000;
        int max1 = 4000000;
        int min2 = 4000000;
        int max2 = 6000000;
        String houseName = "Mono";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;

        // Mocking the behavior of houseRepository.countHouse
        when(houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService))
                .thenReturn(0);

        // Calling the service method
        int result = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);


        // Asserting the result
        assertEquals(0, result);
        verify(houseRepository).countHouse(min1, max1, min2, max2, houseName, type, service, countService);
    }
    @Test
    public void testCountHouse_FullService() {
        // Arrange
        int min1 = 2000000;
        int max1 = 4000000;
        int min2 = 4000000;
        int max2 = 6000000;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(1, 2, 3, 4, 5, 6);
        int countService = 6;

        // Mocking the behavior of houseRepository.countHouse
        when(houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService))
                .thenReturn(0);

        // Calling the service method
        int result = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);


        // Asserting the result
        assertEquals(0, result);

        verify(houseRepository).countHouse(min1, max1, min2, max2, houseName, type, service, countService);
    }
    @Test
    public void testCountHouse_NoType() {
        // Arrange
        int min1 = 2000000;
        int max1 = 4000000;
        int min2 = 4000000;
        int max2 = 6000000;
        String houseName = "";
        List<Integer> type = Arrays.asList();
        List<Integer> service = Arrays.asList(1, 2, 3, 4, 5, 6);
        int countService = 6;

        // Mocking the behavior of houseRepository.countHouse
        when(houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService))
                .thenReturn(0);

        // Calling the service method
        int result = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);


        // Asserting the result
        assertEquals(0, result);

        verify(houseRepository).countHouse(min1, max1, min2, max2, houseName, type, service, countService);
    }
    @Test
    public void testCountHouse_NoService() {
        // Arrange
        int min1 = 2000000;
        int max1 = 4000000;
        int min2 = 4000000;
        int max2 = 6000000;
        String houseName = "";
        List<Integer> type = Arrays.asList(2);
        List<Integer> service = Arrays.asList();
        int countService = 0;

        // Mocking the behavior of houseRepository.countHouse
        when(houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService))
                .thenReturn(0);

        // Calling the service method
        int result = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);


        // Asserting the result
        assertEquals(0, result);

        verify(houseRepository).countHouse(min1, max1, min2, max2, houseName, type, service, countService);
    }


}
