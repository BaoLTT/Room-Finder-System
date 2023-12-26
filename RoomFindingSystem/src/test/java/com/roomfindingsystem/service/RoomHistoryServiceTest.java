package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomHistoriesEntity;
import com.roomfindingsystem.repository.RoomHistoryRepository;
import com.roomfindingsystem.repository.RoomRepository;
import com.roomfindingsystem.service.impl.RoomHistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RoomHistoryServiceTest {
    @InjectMocks
    private RoomHistoryServiceImpl roomHistoryService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomHistoryRepository roomHistoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void updateRoomStatus() {
        // Test data
        int roomId = 1;
        int currentStatus = 1;
        int newStatus = 2;

        // Mock the behavior of roomRepository.getRoomById()
        RoomEntity room = new RoomEntity();
        room.setStatusId(currentStatus);
        when(roomRepository.getRoomById(roomId)).thenReturn(room);

        // Call the method from yourService
        roomHistoryService.updateRoomStatus(roomId, newStatus);

        // Verify that getRoomById was called once
        verify(roomRepository).getRoomById(roomId);

        // Verify that save was called once on roomHistoryRepository
        verify(roomHistoryRepository).save(new RoomHistoriesEntity(roomId, newStatus, LocalDate.now()));
    }

    @Test
    void addRoomHistory() {
        // Test data
        String roomName = "P101";
        int houseId = 1;

        // Mock the behavior of roomRepository.findRoomEntityByRoomNameAndHouseId()
        RoomEntity room = new RoomEntity();
        room.setRoomid(1);
        room.setStatusId(1);
        when(roomRepository.findRoomEntityByRoomNameAndHouseId(roomName, houseId)).thenReturn(room);

        // Call the method from yourService
        roomHistoryService.addRoomHistory(roomName, houseId);

        // Verify that findRoomEntityByRoomNameAndHouseId was called once
        verify(roomRepository).findRoomEntityByRoomNameAndHouseId(roomName, houseId);

        // Verify that save was called once on roomHistoryRepository
        verify(roomHistoryRepository).save(new RoomHistoriesEntity(1, 1, LocalDate.now()));
    }


    @Test
    void addRoomHistory2() {
        // Test data
        String roomName = "";
        int houseId = 1;

        // Mock the behavior of roomRepository.findRoomEntityByRoomNameAndHouseId()
        RoomEntity room = new RoomEntity();
        room.setRoomid(1);
        room.setStatusId(1);
        when(roomRepository.findRoomEntityByRoomNameAndHouseId(roomName, houseId)).thenReturn(room);

        // Call the method from yourService
        roomHistoryService.addRoomHistory(roomName, houseId);

        // Verify that findRoomEntityByRoomNameAndHouseId was called once
        verify(roomRepository).findRoomEntityByRoomNameAndHouseId(roomName, houseId);

        // Verify that save was called once on roomHistoryRepository
        verify(roomHistoryRepository).save(new RoomHistoriesEntity(1, 1, LocalDate.now()));
    }

    @Test
    void addRoomHistory3() {
        // Test data
        String roomName = null;
        int houseId = 1;

        // Mock the behavior of roomRepository.findRoomEntityByRoomNameAndHouseId()
        RoomEntity room = new RoomEntity();
        room.setRoomid(1);
        room.setStatusId(1);
        when(roomRepository.findRoomEntityByRoomNameAndHouseId(roomName, houseId)).thenReturn(room);

        // Call the method from yourService
        roomHistoryService.addRoomHistory(roomName, houseId);

        // Verify that findRoomEntityByRoomNameAndHouseId was called once
        verify(roomRepository).findRoomEntityByRoomNameAndHouseId(roomName, houseId);

        // Verify that save was called once on roomHistoryRepository
        verify(roomHistoryRepository).save(new RoomHistoriesEntity(1, 1, LocalDate.now()));
    }

    @Test
    void addRoomHistory4() {
        // Test data
        String roomName = "P101";
        int houseId = -1;

        // Mock the behavior of roomRepository.findRoomEntityByRoomNameAndHouseId()
        RoomEntity room = new RoomEntity();
        room.setRoomid(1);
        room.setStatusId(1);
        when(roomRepository.findRoomEntityByRoomNameAndHouseId(roomName, houseId)).thenReturn(room);

        // Call the method from yourService
        roomHistoryService.addRoomHistory(roomName, houseId);

        // Verify that findRoomEntityByRoomNameAndHouseId was called once
        verify(roomRepository).findRoomEntityByRoomNameAndHouseId(roomName, houseId);

        // Verify that save was called once on roomHistoryRepository
        verify(roomHistoryRepository).save(new RoomHistoriesEntity(1, 1, LocalDate.now()));
    }

    @Test
    void addRoomHistory5() {
        // Test data
        String roomName = "";
        int houseId = -1;

        // Mock the behavior of roomRepository.findRoomEntityByRoomNameAndHouseId()
        RoomEntity room = new RoomEntity();
        room.setRoomid(1);
        room.setStatusId(1);
        when(roomRepository.findRoomEntityByRoomNameAndHouseId(roomName, houseId)).thenReturn(room);

        // Call the method from yourService
        roomHistoryService.addRoomHistory(roomName, houseId);

        // Verify that findRoomEntityByRoomNameAndHouseId was called once
        verify(roomRepository).findRoomEntityByRoomNameAndHouseId(roomName, houseId);

        // Verify that save was called once on roomHistoryRepository
        verify(roomHistoryRepository).save(new RoomHistoriesEntity(1, 1, LocalDate.now()));
    }

    @Test
    void addRoomHistory6() {
        // Test data
        String roomName = null;
        int houseId = -1;

        // Mock the behavior of roomRepository.findRoomEntityByRoomNameAndHouseId()
        RoomEntity room = new RoomEntity();
        room.setRoomid(1);
        room.setStatusId(1);
        when(roomRepository.findRoomEntityByRoomNameAndHouseId(roomName, houseId)).thenReturn(room);

        // Call the method from yourService
        roomHistoryService.addRoomHistory(roomName, houseId);

        // Verify that findRoomEntityByRoomNameAndHouseId was called once
        verify(roomRepository).findRoomEntityByRoomNameAndHouseId(roomName, houseId);

        // Verify that save was called once on roomHistoryRepository
        verify(roomHistoryRepository).save(new RoomHistoriesEntity(1, 1, LocalDate.now()));
    }
    @Test
    void countEmptyRoomDay() {

        // Test data
        int roomId = 1;

        // Mock the behavior of roomHistoryRepository.findRoomHistoriesEntitiesByRoomid()
        RoomHistoriesEntity history1 = new RoomHistoriesEntity(1, 1, LocalDate.of(2023, 1, 1));
        RoomHistoriesEntity history2 = new RoomHistoriesEntity(1, 0, LocalDate.of(2023, 1, 3));
        RoomHistoriesEntity history3 = new RoomHistoriesEntity(1, 1, LocalDate.of(2023, 1, 5));
        when(roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId))
                .thenReturn(Arrays.asList(history1, history2, history3));

        // Call the method from yourService
        long result = roomHistoryService.countEmptyRoomDay(roomId);

        // Verify the result
        assertEquals(2, result);
    }

    @Test
    void testCountEmptyRoomDay_WithInvalidRoomId() {
        // Mock data
        int roomId = -1;

        // Stub the behavior of the repository to return an empty list for an invalid roomId
        when(roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId)).thenReturn(Collections.emptyList());

        // Call the method to test
        long result = roomHistoryService.countEmptyRoomDay(roomId);

        // Verify that the repository method was called once with the expected roomId
        verify(roomHistoryRepository, times(1)).findRoomHistoriesEntitiesByRoomid(roomId);

        // Assert that the result is as expected (0 in this case, as the list is empty)
        assertEquals(0, result);
    }

    @Test
    void testCountEmptyRoomDay_WithInvalidRoomId1() {
        // Mock data
        int roomId = 0;

        // Stub the behavior of the repository to return an empty list for an invalid roomId
        when(roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId)).thenReturn(Collections.emptyList());

        // Call the method to test
        long result = roomHistoryService.countEmptyRoomDay(roomId);

        // Verify that the repository method was called once with the expected roomId
        verify(roomHistoryRepository, times(1)).findRoomHistoriesEntitiesByRoomid(roomId);

        // Assert that the result is as expected (0 in this case, as the list is empty)
        assertEquals(0, result);
    }

    @Test
    void countRoomDay() {
        // Test data
        int roomId = 1;

        // Mock the behavior of roomHistoryRepository.findRoomHistoriesEntitiesByRoomid()
        RoomHistoriesEntity history1 = new RoomHistoriesEntity(1, 1, LocalDate.of(2023, 1, 1));
        RoomHistoriesEntity history2 = new RoomHistoriesEntity(1, 0, LocalDate.of(2023, 1, 3));
        RoomHistoriesEntity history3 = new RoomHistoriesEntity(1, 1, LocalDate.of(2023, 1, 5));
        when(roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId))
                .thenReturn(Arrays.asList(history1, history2, history3));

        // Call the method from yourService
        long result = roomHistoryService.countRoomDay(roomId);

        // Verify the result
        assertEquals(4, result);
    }

    @Test
    void testCountRoomDay_WithInvalidRoomId() {
        // Mock data
        int roomId = -1;

        // Stub the behavior of the repository to return an empty list for an invalid roomId
        when(roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId)).thenReturn(Collections.emptyList());

        // Call the method to test
        long result = roomHistoryService.countEmptyRoomDay(roomId);

        // Verify that the repository method was called once with the expected roomId
        verify(roomHistoryRepository, times(1)).findRoomHistoriesEntitiesByRoomid(roomId);

        // Assert that the result is as expected (0 in this case, as the list is empty)
        assertEquals(0, result);
    }

    @Test
    void testCountRoomDay_WithInvalidRoomId1() {
        // Mock data
        int roomId = 0;

        // Stub the behavior of the repository to return an empty list for an invalid roomId
        when(roomHistoryRepository.findRoomHistoriesEntitiesByRoomid(roomId)).thenReturn(Collections.emptyList());

        // Call the method to test
        long result = roomHistoryService.countEmptyRoomDay(roomId);

        // Verify that the repository method was called once with the expected roomId
        verify(roomHistoryRepository, times(1)).findRoomHistoriesEntitiesByRoomid(roomId);

        // Assert that the result is as expected (0 in this case, as the list is empty)
        assertEquals(0, result);
    }

}