package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ServiceRoomEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ServiceRoomRepositoryTest {
    @Autowired
    private ServiceRoomRepository serviceRoomRepository;
    @Test
    @Transactional
    void deleteByRoomIdAndServiceId() {
        serviceRoomRepository.deleteByRoomIdAndServiceId(245,1);

        List<ServiceRoomEntity> list =  serviceRoomRepository.findAllByRoomId(245);
        assertEquals(0, list.size());
//        assertEquals();
    }

    @Test
    void findAllByRoomId() {
       List<ServiceRoomEntity> list =  serviceRoomRepository.findAllByRoomId(245);

       assertNotNull(list);
       assertEquals(1, list.size());
    }
}