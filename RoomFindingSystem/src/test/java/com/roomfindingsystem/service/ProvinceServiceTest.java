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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvinceServiceTest {
    @InjectMocks
    private ProvinceServiceImpl provinceService;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private ModelMapper modelMapper;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
    @Test
    void testGetAllProvinces() {
        // Mock data
        List<ProvinceEntity> expectedProvinces = new ArrayList<>();
        ProvinceEntity province1 = new ProvinceEntity(); province1.setName("a");
        ProvinceEntity province2 = new ProvinceEntity(); province1.setName("b");
        expectedProvinces.add(province1);
        expectedProvinces.add(province2);

        // Stub the behavior of the repository
        when(provinceRepository.findAll()).thenReturn(expectedProvinces);

        // Call the method to test
        List<ProvinceEntity> actualProvinces = provinceService.getAll();

        // Verify interactions and assertions
        assertNotNull(actualProvinces);


        for (int i = 0; i < expectedProvinces.size(); i++) {
//            assertEquals(expectedProvinces.get(i).getId(), actualProvinces.get(i).getId());
            assertEquals(expectedProvinces.get(i).getName(), actualProvinces.get(i).getName());
        }

        // Verify that the repository method was called once
        verify(provinceRepository, times(1)).findAll();
    }


    @Test
    void testGetProvinceById() {
        // Mock data
        int expectedProvinceId = 1;
        ProvinceEntity expectedProvince = new ProvinceEntity();
        expectedProvince.setProvinceId(expectedProvinceId);
        expectedProvince.setName("a");

        // Stub the behavior of the repository
        when(provinceRepository.getProvinceById(expectedProvinceId)).thenReturn(expectedProvince);

        // Call the method to test
        ProvinceEntity actualProvince = provinceService.getProvinceById(expectedProvinceId);

        // Verify interactions and assertions
        assertNotNull(actualProvince);
        assertEquals(expectedProvinceId, actualProvince.getProvinceId());
        assertEquals(expectedProvince.getName(), actualProvince.getName());

        // Verify that the repository method was called once with the expected id
        verify(provinceRepository, times(1)).getProvinceById(expectedProvinceId);
    }

    @Test
    void testGetProvinceById_EntityNotFound() {
        // Mock data
        int nonExistentProvinceId = 153;

        // Stub the behavior of the repository to return null for non-existent entity
        when(provinceRepository.getProvinceById(nonExistentProvinceId)).thenReturn(null);

        // Call the method to test
        ProvinceEntity actualProvince = provinceService.getProvinceById(nonExistentProvinceId);

        // Verify interactions and assertions
        assertNull(actualProvince);

        // Verify that the repository method was called once with the expected id
        verify(provinceRepository, times(1)).getProvinceById(nonExistentProvinceId);
    }

    @Test
    void testGetProvinceById_EntityNotFound1() {
        // Mock data
        int nonExistentProvinceId = -1;

        // Stub the behavior of the repository to return null for non-existent entity
        when(provinceRepository.getProvinceById(nonExistentProvinceId)).thenReturn(null);

        // Call the method to test
        ProvinceEntity actualProvince = provinceService.getProvinceById(nonExistentProvinceId);

        // Verify interactions and assertions
        assertNull(actualProvince);

        // Verify that the repository method was called once with the expected id
        verify(provinceRepository, times(1)).getProvinceById(nonExistentProvinceId);
    }

    @Test
    void testGetProvinceById_EntityNotFound2() {
        // Mock data
        int nonExistentProvinceId = 0;

        // Stub the behavior of the repository to return null for non-existent entity
        when(provinceRepository.getProvinceById(nonExistentProvinceId)).thenReturn(null);

        // Call the method to test
        ProvinceEntity actualProvince = provinceService.getProvinceById(nonExistentProvinceId);

        // Verify interactions and assertions
        assertNull(actualProvince);

        // Verify that the repository method was called once with the expected id
        verify(provinceRepository, times(1)).getProvinceById(nonExistentProvinceId);
    }
}