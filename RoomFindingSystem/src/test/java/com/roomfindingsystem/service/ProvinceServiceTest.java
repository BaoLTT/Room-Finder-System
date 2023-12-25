package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.ProvinceEntity;
import com.roomfindingsystem.repository.ProvinceRepository;
import com.roomfindingsystem.service.impl.ProvinceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvinceServiceTest {
    @InjectMocks
    private ProvinceServiceImpl provinceService;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAll() {
        // Test data
        List<ProvinceEntity> expectedProvinces = Arrays.asList(
                new ProvinceEntity(/* initialize with relevant data */),
                new ProvinceEntity(/* initialize with relevant data */)
                // Add more ProvinceEntity objects as needed
        );

        // Mock the behavior of provinceRepository.findAll()
//        when(provinceRepository.findAll()).thenReturn(expectedProvinces);

        // Call the method from yourService
        List<ProvinceEntity> result = provinceService.getAll();



        // Assert the result
        assertEquals(0, result.size());
    }
}