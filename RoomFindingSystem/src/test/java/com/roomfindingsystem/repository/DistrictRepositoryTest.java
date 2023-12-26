package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.DistrictEntity;
import com.roomfindingsystem.entity.ProvinceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DistrictRepositoryTest {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;
    @Test
    void findByProvinceId() {
        ProvinceEntity province = provinceRepository.getProvinceById(2);

        assertNotNull(province);
        assertEquals("Tỉnh", province.getCode());
    }

    @Test
    void testGetDistrictById_WithValidId() {
        // Arrange
        int validDistrictId = 1;


        // Act
        DistrictEntity resultDistrict = districtRepository.getDistrictById(validDistrictId);

        // Assert
        assertEquals("Quận Ba Đình", resultDistrict.getName());

    }
    @Test
    void testGetDistrictById_WithInvalidId2() {
        // Arrange
        int invalidDistrictId = 0;  // Giả sử districtId là một giá trị không hợp lệ


        // Act
        DistrictEntity resultDistrict = districtRepository.getDistrictById(invalidDistrictId);

        // Assert
        // Kiểm tra xem kết quả có phải là null hay không
        assertNull(resultDistrict);
    }
}