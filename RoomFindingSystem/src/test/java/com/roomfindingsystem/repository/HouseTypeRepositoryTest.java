package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.TypeHouseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HouseTypeRepositoryTest {
    @Autowired
    private HouseTypeRepository houseTypeRepository;

    @Test
    void findAll() {
       List<TypeHouseEntity> list = houseTypeRepository.findAll();

       assertNotNull(list);
       assertEquals(5, list.size());
    }

    @Test
    void findTypeNotUse() {
       List<TypeHouseEntity> list = houseTypeRepository.findTypeNotUse();

        assertEquals(0, list.size());
    }
}