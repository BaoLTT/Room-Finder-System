package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.RoomHouseDetailDto;
import com.roomfindingsystem.repository.RoomRepository;
import com.roomfindingsystem.service.impl.RoomServiceImpl;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Tuple createMockTuple() {
        return new Tuple() {
            @Override
            public <X> X get(TupleElement<X> tupleElement) {
                return null;
            }

            @Override
            public <X> X get(String alias, Class<X> type) {
                return null;
            }

            @Override
            public Object get(String alias) {
                return null;
            }

            @Override
            public <X> X get(int i, Class<X> type) {
                return switch (i) {
                    case 0 -> type.cast(2);
                    case 1 -> type.cast(1);
                    case 2 -> type.cast("Phòng Đôi");
                    case 3 -> type.cast(1);
                    case 4 -> type.cast("TONY HOUSE");
                    case 5 -> type.cast(4000000);
                    case 6 -> type.cast("2,257");
                    case 7 -> type.cast(null);
                    // Thêm các trường khác nếu có
                    default -> throw new IllegalArgumentException("Index out of bounds: " + i);
                };
            }

            @Override
            public Object get(int i) {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public List<TupleElement<?>> getElements() {
                return null;
            }

        };
    }

            //test viewRoomInHouse()
    @Test
    void testViewRoomInHouseWithValidInput() {
        // Arrange

    }

    @Test
    void testViewRoomInHouseWithEmptyResult() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }

    
}
