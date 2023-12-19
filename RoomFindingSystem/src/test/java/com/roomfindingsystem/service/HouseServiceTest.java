package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.HouseDto;
import com.roomfindingsystem.dto.HouseImageLink;
import com.roomfindingsystem.dto.HouseTypeVo;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.repository.HouseRepository;
import com.roomfindingsystem.service.impl.HouseServiceImpl;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {
    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private HouseServiceImpl houseService;
    @Test
    void testFindAllDetailWithValidHouseId() {
        // Arrange
        int houseId = 1;

        // Mock data for HouseDto
        HouseDto houseDto = new HouseDto(); houseDto.setHouseName("TONY HOUSE");
        List<HouseDto> houseDtoList = Collections.singletonList(houseDto);

        when(houseRepository.findAllDetail(houseId)).thenReturn(houseDtoList);

        // Act
        List<HouseDto> result = houseService.getHouseDetail(houseId);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(houseDto, result.get(0)); // Assuming you expect a single DTO in the result

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).findAllDetail(houseId);
    }

    @Test
    void testFindAllDetailWithEmptyResult() {
        // Arrange
        int houseId = 20000; // Assuming id with no details

        when(houseRepository.findAllDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<HouseDto> result = houseService.getHouseDetail(houseId);

        // Assert
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).findAllDetail(houseId);
    }

    //test
    @Test
    void testGetServiceByIdWithValidId() {
        // Arrange
        int id = 1;

        // Mock data for ServiceDto
        ServiceDto serviceDto = new ServiceDto(); serviceDto.setServiceId(1);
        List<ServiceDto> serviceDtoList = Collections.singletonList(serviceDto);

        when(houseRepository.getServiceById(id)).thenReturn(serviceDtoList);

        // Act
        List<ServiceDto> result = houseService.getServiceById(id);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(serviceDto.getServiceId(), result.get(0).getServiceId()); // Assuming you expect a single DTO in the result

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).getServiceById(id);
    }

    @Test
    void testGetServiceByIdWithEmptyResult() {
        // Arrange
        int id = 273; // Assuming id with no services

        when(houseRepository.getServiceById(id)).thenReturn(Collections.emptyList());

        // Act
        List<ServiceDto> result = houseService.getServiceById(id);

        // Assert
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getServiceById(id);
    }

    //test getImage
    @Test
    void testGetImageByIdWithValidId() {
        // Arrange
        int id = 1;

        // Mock data for HouseImageLink
        HouseImageLink imageLink = new HouseImageLink(); imageLink.setHouseImagesEntities("/rfs_bucket/House/house_2_1.jpg");
        List<HouseImageLink> imageLinkList = Collections.singletonList(imageLink);

        when(houseRepository.getByHouseImageid(id)).thenReturn(imageLinkList);

        // Act
        List<HouseImageLink> result = houseService.getImageById(id);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(imageLink.getHouseImagesEntities(), result.get(0).getHouseImagesEntities()); // Assuming you expect a single image link

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getByHouseImageid(id);
    }

    @Test
    void testGetImageByIdWithEmptyResult() {
        // Arrange
        int id = 273; // Assuming id with no images

        when(houseRepository.getByHouseImageid(id)).thenReturn(Collections.emptyList());

        // Act
        List<HouseImageLink> result = houseService.getImageById(id);

        // Assert
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getByHouseImageid(id);
    }

    //test getHouseById
    @Test
    void testGetHouseByIdWithValidId() {
        // Arrange
        int id = 1;

        // Mock data for HousesEntity
        HousesEntity housesEntity = new HousesEntity(/* provide necessary details */);

        when(houseRepository.getHousesEntitiesByHouseId(id)).thenReturn(housesEntity);

        // Act
        HousesEntity result = houseService.getHouseById(id);

        // Assert
        assertNotNull(result);
        assertEquals(housesEntity, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getHousesEntitiesByHouseId(id);
    }

    @Test
    void testGetHouseByIdWithEmptyResult() {
        // Arrange
        int id = 2000; // Assuming id with no data

        when(houseRepository.getHousesEntitiesByHouseId(id)).thenReturn(null);

        // Act
        HousesEntity result = houseService.getHouseById(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getHousesEntitiesByHouseId(id);
    }

    @Test
    void testFindHouse() {

    }
    @Test
    void testCountHouse() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = 2000000;
        int min2 = 2000000;
        int max2 = 4000000;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 0;
        String houseName = "";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int countService = 1;

        int countHouse = 1;

        when(houseRepository.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2,  houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2,  houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2,  houseName, type, service, countService);
    }

}
