package com.example.roomfindingsystem.service;


import com.roomfindingsystem.dto.HouseDto;
import com.roomfindingsystem.repository.HouseRepository;
import com.roomfindingsystem.service.impl.HouseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {
    @Mock
    HouseRepository houseRepository;
    @InjectMocks
    HouseServiceImpl houseService;

    //Test getHouseDetail
    //Test case 1: valid houseId
    @Test
    void testGetHouseDetailNotEmpty() {
        // Arrange
        int houseId = 1;

        HouseDto houseDto = new HouseDto();
        houseDto.setHouseName("TONY HOUSE");
        List<HouseDto> houseDtoList = Arrays.asList(houseDto);

        when(houseRepository.findAllDetail(houseId)).thenReturn(houseDtoList);

        // Act
        List<HouseDto> result = houseService.getHouseDetail(houseId);

        // Assert
        assertEquals(houseDtoList.get(0).getHouseName(), result.get(0).getHouseName());
        // Add more assertions based on your business logic and expectations

        // Verify
        verify(houseRepository, times(1)).findAllDetail(houseId);
    }

}
