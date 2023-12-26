package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.DistrictEntity;
import com.roomfindingsystem.repository.DistrictRepository;
import com.roomfindingsystem.repository.UserRepository;
import com.roomfindingsystem.service.impl.AdminManageUserServiceImpl;
import com.roomfindingsystem.service.impl.DistrictServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class DistrictServiceTest {
    @Mock
    DistrictRepository districtRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    DistrictServiceImpl districtServiceImpl;
    @Test
    void getAll() {
        DistrictEntity district1 = new DistrictEntity();
        district1.setDistrictId(1);
        district1.setName("District 1");

        DistrictEntity district2 = new DistrictEntity();
        district2.setDistrictId(2);
        district2.setName("District 2");

        // Set up the mock behavior for findAll
        when(districtRepository.findAll()).thenReturn(Arrays.asList(district1, district2));

        // Call the method you want to test
        List<DistrictEntity> result = districtServiceImpl.getAll();

        // Verify that findAll was called
        verify(districtRepository, times(1)).findAll();

        // Verify that the result is as expected
        assertEquals(2, result.size());

        DistrictEntity resultDistrict1 = result.get(0);
        assertEquals(1, resultDistrict1.getDistrictId());
        assertEquals("District 1", resultDistrict1.getName());

        DistrictEntity resultDistrict2 = result.get(1);
        assertEquals(2, resultDistrict2.getDistrictId());
        assertEquals("District 2", resultDistrict2.getName());
    }

    @Test
    void getDistrictsByProvince() {
        // Mocking data
        Integer provinceId = 1;
        DistrictEntity district1 = new DistrictEntity();
        district1.setDistrictId(1);
        district1.setName("District 1");

        DistrictEntity district2 = new DistrictEntity();
        district2.setDistrictId(2);
        district2.setName("District 2");

        // Mocking repository behavior
        when(districtRepository.findByProvinceId(provinceId))
                .thenReturn(Arrays.asList(district1, district2));

        // Call the method you want to test
        List<DistrictEntity> result = districtServiceImpl.getDistrictsByProvince(provinceId);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("District 1", result.get(0).getName());
        assertEquals("District 2", result.get(1).getName());
    }

    @Test
    void getDistrictsByProvince1() {
        // Mocking data
        Integer provinceId = 2;
        DistrictEntity district1 = new DistrictEntity();
        district1.setDistrictId(2);
        district1.setName("District 1");

        DistrictEntity district2 = new DistrictEntity();
        district2.setDistrictId(2);
        district2.setName("District 2");

        // Mocking repository behavior
        when(districtRepository.findByProvinceId(provinceId))
                .thenReturn(Arrays.asList(district1, district2));

        // Call the method you want to test
        List<DistrictEntity> result = districtServiceImpl.getDistrictsByProvince(provinceId);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("District 1", result.get(0).getName());
        assertEquals("District 2", result.get(1).getName());
    }

    @Test
    void getDistrictsByProvince2() {
        // Mocking data
        Integer provinceId = 1000;
        DistrictEntity district1 = new DistrictEntity();
        district1.setDistrictId(2);
        district1.setName("District 1");

        DistrictEntity district2 = new DistrictEntity();
        district2.setDistrictId(2);
        district2.setName("District 2");

        // Mocking repository behavior
        when(districtRepository.findByProvinceId(provinceId))
                .thenReturn(Arrays.asList(district1, district2));

        // Call the method you want to test
        List<DistrictEntity> result = districtServiceImpl.getDistrictsByProvince(provinceId);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("District 1", result.get(0).getName());
        assertEquals("District 2", result.get(1).getName());
    }

    @Test
    void getDistrictsByProvince3() {
        // Mocking data
        Integer provinceId = null;
        DistrictEntity district1 = new DistrictEntity();
        district1.setDistrictId(2);
        district1.setName("District 1");

        DistrictEntity district2 = new DistrictEntity();
        district2.setDistrictId(2);
        district2.setName("District 2");

        List<DistrictEntity> newlist = new ArrayList<>();
        // Mocking repository behavior
        when(districtRepository.findByProvinceId(provinceId))
                .thenReturn(newlist);
        // Call the method you want to test
        List<DistrictEntity> result = districtServiceImpl.getDistrictsByProvince(provinceId);

        // Verify the result
        assertEquals(0, result.size());

    }

    //getDistricById
    @Test
    void testGetDistrictById_WithValidId() {
        // Arrange
        int validDistrictId = 1;

        DistrictEntity mockedDistrictEntity = new DistrictEntity();
        mockedDistrictEntity.setDistrictId(validDistrictId);
        mockedDistrictEntity.setName("A");


        when(districtRepository.getDistrictById(validDistrictId)).thenReturn(mockedDistrictEntity);

        // Act
        DistrictEntity resultDistrict = districtServiceImpl.getDistrictById(validDistrictId);

        // Assert
        assertEquals(mockedDistrictEntity, resultDistrict);

        // Verify that the repository method was called with the correct argument
        verify(districtRepository).getDistrictById(validDistrictId);

        // Verify that there were no other interactions with the repository
        verifyNoMoreInteractions(districtRepository);
    }

    @Test
    void testGetDistrictById_WithValidId2() {
        // Arrange
        int validDistrictId = 2;

        DistrictEntity mockedDistrictEntity = new DistrictEntity();
        mockedDistrictEntity.setDistrictId(validDistrictId);
        mockedDistrictEntity.setName("A");


        when(districtRepository.getDistrictById(validDistrictId)).thenReturn(mockedDistrictEntity);

        // Act
        DistrictEntity resultDistrict = districtServiceImpl.getDistrictById(validDistrictId);

        // Assert
        assertEquals(mockedDistrictEntity, resultDistrict);

        // Verify that the repository method was called with the correct argument
        verify(districtRepository).getDistrictById(validDistrictId);

        // Verify that there were no other interactions with the repository
        verifyNoMoreInteractions(districtRepository);
    }

    @Test
    void testGetDistrictById_WithInvalidId() {
        // Arrange
        int invalidDistrictId = 100000;  // Giả sử districtId là một giá trị không hợp lệ

        // Mô phỏng hành vi của districtRepository khi không tìm thấy DistrictEntity
        when(districtRepository.getDistrictById(invalidDistrictId)).thenReturn(null);

        // Act
        DistrictEntity resultDistrict = districtServiceImpl.getDistrictById(invalidDistrictId);

        // Assert
        // Kiểm tra xem kết quả có phải là null hay không
        assertNull(resultDistrict);

        // Verify that the repository method was called with the correct argument
        verify(districtRepository).getDistrictById(invalidDistrictId);

        // Verify that there were no other interactions with the repository
        verifyNoMoreInteractions(districtRepository);
    }

    @Test
    void testGetDistrictById_WithInvalidId2() {
        // Arrange
        int invalidDistrictId = 0;  // Giả sử districtId là một giá trị không hợp lệ

        // Mô phỏng hành vi của districtRepository khi không tìm thấy DistrictEntity
        when(districtRepository.getDistrictById(invalidDistrictId)).thenReturn(null);

        // Act
        DistrictEntity resultDistrict = districtServiceImpl.getDistrictById(invalidDistrictId);

        // Assert
        // Kiểm tra xem kết quả có phải là null hay không
        assertNull(resultDistrict);

        // Verify that the repository method was called with the correct argument
        verify(districtRepository).getDistrictById(invalidDistrictId);

        // Verify that there were no other interactions with the repository
        verifyNoMoreInteractions(districtRepository);
    }


}