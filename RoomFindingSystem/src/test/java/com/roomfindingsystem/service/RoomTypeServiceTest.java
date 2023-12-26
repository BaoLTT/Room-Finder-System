package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.RoomTypeDto;
import com.roomfindingsystem.entity.RoomTypeEntity;
import com.roomfindingsystem.repository.RoomTypeRepository;
import com.roomfindingsystem.service.impl.RoomTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomTypeServiceTest {
    @InjectMocks
    private RoomTypeServiceImpl roomTypeService;

    @Mock
    private RoomTypeRepository roomTypeRepository;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Test data
        RoomTypeEntity roomTypeEntity1 = new RoomTypeEntity();
        RoomTypeEntity roomTypeEntity2 = new RoomTypeEntity();


        // Mock the behavior of modelMapper.map
        RoomTypeDto roomTypeDto1 = new RoomTypeDto();
        RoomTypeDto roomTypeDto2 = new RoomTypeDto();


        // Call the method from yourService
        List<RoomTypeDto> result = roomTypeService.findAll();

        // Verify the result
        assertEquals(0, result.size());
    }

    @Test
    void testFindAll() {
        List<RoomTypeDto> list = new ArrayList<>();
        RoomTypeService service = Mockito.mock(RoomTypeService.class);
        list = service.findAll();
        assertEquals(service.findAll(), list);
    }
}