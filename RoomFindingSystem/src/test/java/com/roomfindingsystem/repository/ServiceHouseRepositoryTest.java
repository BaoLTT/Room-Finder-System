package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ServiceHouseEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ServiceHouseRepositoryTest {
    @Autowired
    private ServiceHouseRepository serviceHouseRepository;
    @Test
    @Transactional
    void deleteByHouseId() {
        serviceHouseRepository.deleteByHouseId(10);

        List<ServiceHouseEntity> list = serviceHouseRepository.findAll();

        assertEquals(82, list.size());
    }
}