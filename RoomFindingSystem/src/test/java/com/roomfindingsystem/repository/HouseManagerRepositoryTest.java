package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HousesEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HouseManagerRepositoryTest {
    @Autowired
    private HouseManagerRepository houseManagerRepository;
    @Test
    void findHouseManager() {
        List<HouseManagerTypeVo> list = houseManagerRepository.findHouseManager();

        assertNotNull(list);
    }

    @Test
    void findHouseById() {
        HouseManagerTypeVo houseManagerTypeVo = houseManagerRepository.findHouseById(1);

        assertNotNull(houseManagerTypeVo);
        assertEquals("TONY HOUSE", houseManagerTypeVo.getHouseName());
    }

    @Test
    void getLastHouse() {
       HousesEntity housesEntity = houseManagerRepository.getLastHouse();

        assertNotNull(housesEntity);
        assertEquals("a", housesEntity.getHouseName());
    }

    @Test
    @Transactional
    void updateHouse() {
        houseManagerRepository.updateHouse("a", 1, "Khong co gi", 1, null, 1, 357, null, null);

        HouseManagerTypeVo houseManagerTypeVo = houseManagerRepository.findHouseById(357);

        assertNotNull(houseManagerTypeVo);
        assertEquals("a", houseManagerTypeVo.getHouseName() );
    }
}