package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.TypeHouseEntity;
import com.roomfindingsystem.repository.HouseTypeRepository;
import com.roomfindingsystem.service.impl.HouseTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseTypeServiceTest {
    @InjectMocks
    private HouseTypeServiceImpl houseTypeService;

    @Mock
    private HouseTypeRepository houseTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAll() {

        // Mock data
        TypeHouseEntity typeHouseEntity1 = new TypeHouseEntity();
        typeHouseEntity1.setTypeId(1);
        typeHouseEntity1.setTypeName("Type 1");

        TypeHouseEntity typeHouseEntity2 = new TypeHouseEntity();
        typeHouseEntity2.setTypeId(2);
        typeHouseEntity2.setTypeName("Type 2");

        List<TypeHouseEntity> mockData = Arrays.asList(typeHouseEntity1, typeHouseEntity2);

        // Mock behavior of the repository method
        when(houseTypeRepository.findAll()).thenReturn(mockData);

        // Call the method from yourService
        List<TypeHouseEntity> result = houseTypeService.findAll();

        // Verify that the expected method was called with the correct arguments
        verify(houseTypeRepository).findAll();

        // Assert the result
        assertEquals(mockData, result);
    }

    @Test
    void findTypeNotUse() {
        // Test data
        List<TypeHouseEntity> expectedTypes = new ArrayList<>(); // replace with your actual return type
        // Mock the repository method
        when(houseTypeRepository.findTypeNotUse()).thenReturn(expectedTypes);

        // Call the method from yourService
        List<TypeHouseEntity> result = houseTypeService.findTypeNotUse();

        // Verify that the expected method was called
        verify(houseTypeRepository, times(1)).findTypeNotUse();

        // Assert the result
        assertEquals(expectedTypes, result);
    }

    @Test
    void deleteType1() {
        // Test data
        Integer typeIdToDelete = 1; // replace with your actual type ID

        // Call the method from yourService
        houseTypeService.deleteType(typeIdToDelete);

        // Verify that the deleteById method was called with the correct argument
        verify(houseTypeRepository, times(1)).deleteById(typeIdToDelete);
    }
    @Test
    void deleteType2() {
        // Test data
        Integer typeIdToDelete = 5; // replace with your actual type ID

        // Call the method from yourService
        houseTypeService.deleteType(typeIdToDelete);

        // Verify that the deleteById method was called with the correct argument
        verify(houseTypeRepository, times(1)).deleteById(typeIdToDelete);
    }

    @Test
    void deleteType3() {
        // Test data
        Integer typeIdToDelete = 0; // replace with your actual type ID

        // Call the method from yourService
        houseTypeService.deleteType(typeIdToDelete);

        // Verify that the deleteById method was called with the correct argument
        verify(houseTypeRepository, times(1)).deleteById(typeIdToDelete);
    }

    @Test
    void deleteType4() {
        // Test data
        Integer typeIdToDelete = -1; // replace with your actual type ID

        // Call the method from yourService
        houseTypeService.deleteType(typeIdToDelete);

        // Verify that the deleteById method was called with the correct argument
        verify(houseTypeRepository, times(1)).deleteById(typeIdToDelete);
    }

    @Test
    void addType1() {

        // Test data
        TypeHouseEntity typeHouseEntity = new TypeHouseEntity(); // replace with your actual TypeHouseEntity object

        // Call the method from yourService
        houseTypeService.addType(typeHouseEntity);

        // Verify that the save method was called with the correct argument
        verify(houseTypeRepository, times(1)).save(typeHouseEntity);

    }

    @Test
    void addType2() {

        // Test data
        TypeHouseEntity typeHouseEntity = new TypeHouseEntity(); // replace with your actual TypeHouseEntity object
        typeHouseEntity = null;
        // Call the method from yourService
        houseTypeService.addType(typeHouseEntity);

        // Verify that the save method was called with the correct argument
        verify(houseTypeRepository, times(1)).save(typeHouseEntity);

    }
}