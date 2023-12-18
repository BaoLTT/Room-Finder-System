package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceDetailRepositoryTest {
    @Autowired
    private ServiceDetailRepository serviceDetailRepository;

    @Test
    void testGetAllService() {
        // Arrange
        int houseId = 1;

        // Mock data for HouseDto
        List<ServiceDetailEntity> getAll = serviceDetailRepository.getAll();

        assertEquals(getAll.size()>0, true); // Assuming you expect a single DTO in the result

    }
}
