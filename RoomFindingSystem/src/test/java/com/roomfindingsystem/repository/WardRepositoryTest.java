package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.WardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WardRepositoryTest {
    @Autowired
    private WardRepository wardRepository;
    @Test
    void findByDistrictIdAndProvinceId() {
        List<WardEntity> list = wardRepository.findByDistrictIdAndProvinceId(1,1);

        assertEquals(14, list.size());
    }
}