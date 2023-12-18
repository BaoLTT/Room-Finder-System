package com.roomfindingsystem.repository;



import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HouseLandlordRepositoryTest {
    @Autowired
    private HouseLandlordRepository houseLandlordRepository;

    @Test
    void testFindAllHouse() {

        List<Tuple> result = houseLandlordRepository.getAllHouse();

        // Assert
        assertTrue(!result.isEmpty());

    }
}
