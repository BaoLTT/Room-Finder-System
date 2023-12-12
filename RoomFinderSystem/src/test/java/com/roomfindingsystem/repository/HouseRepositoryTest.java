package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.HouseDto;
import com.roomfindingsystem.dto.HouseImageLink;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.HousesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HouseRepositoryTest {
    @Autowired
    private HouseRepository houseRepository;

    @Test
    void testFindAllDetailWithValidHouseId() {
        // Arrange
        int houseId = 1;

        // Mock data for HouseDto
        HouseDto houseDto = new HouseDto(); houseDto.setHouseName("TONY HOUSE");
        List<HouseDto> houseDtoList = Collections.singletonList(houseDto);


        // Act
        List<HouseDto> result = houseRepository.findAllDetail(houseId);

        // Assert
        assertEquals(houseDto.getHouseName(), result.get(0).getHouseName()); // Assuming you expect a single DTO in the result

    }

    @Test
    void testFindAllDetailWithEmptyResult() {
        // Arrange
        int houseId = 2000;
        // Act
        List<HouseDto> result = houseRepository.findAllDetail(houseId);

        // Assert
        assertTrue(result.isEmpty());

    }

    //test
    @Test
    void testGetServiceByIdWithValidId() {
        // Arrange
        int id = 1;

        // Mock data for ServiceDto
        ServiceDto serviceDto = new ServiceDto(); serviceDto.setServiceId(1);
        List<ServiceDto> serviceDtoList = Collections.singletonList(serviceDto);


        // Act
        List<ServiceDto> result = houseRepository.getServiceById(id);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(serviceDto.getServiceId(), result.get(0).getServiceId()); // Assuming you expect a single DTO in the result


    }

    @Test
    void testGetServiceByIdWithEmptyResult() {
        // Arrange
        int id = 273; // Assuming id with no services


        // Act
        List<ServiceDto> result = houseRepository.getServiceById(id);

        // Assert
        assertTrue(result.isEmpty());

    }

    //test getImage
    @Test
    void testGetImageByIdWithValidId() {
        // Arrange
        int id = 1;

        // Act
        List<HouseImageLink> result = houseRepository.getByHouseImageid(id);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals("/rfs_bucket/House/house_2_1.jpg", result.get(0).getHouseImagesEntities()); // Assuming you expect a single image link


    }

    @Test
    void testGetImageByIdWithEmptyResult() {
        // Arrange
        int id = 273; // Assuming id with no images

        // Act
        List<HouseImageLink> result = houseRepository.getByHouseImageid(id);

        // Assert
        assertTrue(result.isEmpty());

    }

    //test getHouseById
    @Test
    void testGetHouseByIdWithValidId() {
        // Arrange
        int id = 1;

        // Act
        HousesEntity result = houseRepository.getHousesEntitiesByHouseId(id);

        // Assert
        assertNotNull(result);
        assertEquals("TONY HOUSE", result.getHouseName());

    }

    @Test
    void testGetHouseByIdWithEmptyResult() {
        // Arrange
        int id = 2000; // Assuming id with no data


        // Act
        HousesEntity result = houseRepository.getHousesEntitiesByHouseId(id);

        // Assert
        assertNull(result);

    }


}
