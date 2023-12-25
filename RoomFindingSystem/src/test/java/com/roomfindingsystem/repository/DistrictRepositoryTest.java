package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ProvinceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DistrictRepositoryTest {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Test
    void findByProvinceId() {
        ProvinceEntity province = provinceRepository.getProvinceById(2);

        assertNotNull(province);
        assertEquals("Tỉnh", province.getCode());
    }
}