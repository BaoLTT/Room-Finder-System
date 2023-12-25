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
class TypeHouseRepositoryTest {
    @Autowired
    private TypeHouseRepository typeHouseRepository;

    @Test
    void findAll() {
        List<TypeHouseEntity> list = typeHouseRepository.findAll();

        assertEquals(5, list.size());
    }
}