package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.RoomHouseDetailDto;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

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
        int houseId = 1;

        // Mock data for Tuple
        /* provide necessary details */
        Tuple tuple = createMockTuple();
        List<Tuple> tuples = List.of(tuple);

        // Act
        List<Tuple> result = roomRepository.viewRoomInHouseDetail(houseId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());

    }

    @Test
    void testViewRoomInHouseWithEmptyResult() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data


        // Act
        List<Tuple> result = roomRepository.viewRoomInHouseDetail(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }
}
