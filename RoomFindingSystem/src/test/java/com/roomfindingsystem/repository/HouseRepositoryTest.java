package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.HouseDto;
import com.roomfindingsystem.dto.HouseImageLink;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.HousesEntity;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HouseRepositoryTest {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseLandlordRepository houseLandlordRepository;

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

    @Test
    void testFindHouse() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = 2000000;
        int min2 = 2000000;
        int max2 = 4000000;
        int province = 1;
        int district = 276;
        int ward = 10000;
        int status1 = 1;
        int status2 = 0;
        String houseName = "TONY HOUSE";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int countService = 1;
        int pageIndex = 1;
        int pageSize = 5;



        // Act
        List<Tuple> findHouse = houseRepository.findHouse(min1, max1, min2, max2,
        province, district, ward, status1, status2,  houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(findHouse);

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



        // Act
        int countHouse = houseRepository.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2,  houseName, type, service, countService);

        // Assert
        assertEquals(countHouse > 0, true);

    }
    @Test
    void testFindAllHouse() {

        List<Tuple> result = houseLandlordRepository.getAllHouse();

        // Assert
        assertTrue(!result.isEmpty());

    }






}
