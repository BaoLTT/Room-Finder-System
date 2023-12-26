package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.repository.FeedbackRepository;
import com.roomfindingsystem.repository.HouseRepository;
import com.roomfindingsystem.service.impl.HouseServiceImpl;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


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
        HouseDto houseDto = new HouseDto();
        houseDto.setHouseName("TONY HOUSE");
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
    void testFindAllDetailWithInvalid() {
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

    @Test
    void testFindAllDetailWithZero() {
        // Arrange
        int houseId = 0; // Assuming id with no details

        when(houseRepository.findAllDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<HouseDto> result = houseService.getHouseDetail(houseId);

        // Assert
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).findAllDetail(houseId);
    }

    @Test
    void testFindAllDetailWithNegative() {
        // Arrange
        int houseId = -1; // Assuming id with no details

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
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setServiceId(1);
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
    void testGetServiceByIdWithValidId2() {
        // Arrange
        int id = 2;

        // Mock data for ServiceDto
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setServiceId(1);
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
        int id = -1; // Assuming id with no services

        when(houseRepository.getServiceById(id)).thenReturn(Collections.emptyList());

        // Act
        List<ServiceDto> result = houseService.getServiceById(id);

        // Assert
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getServiceById(id);
    }

    @Test
    void testGetServiceByIdWithEmptyResult2() {
        // Arrange
        int id = 0; // Assuming id with no services

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
        HouseImageLink imageLink = new HouseImageLink();
        imageLink.setHouseImagesEntities("/rfs_bucket/House/house_2_1.jpg");
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
        int id = 2;

        when(houseRepository.getByHouseImageid(id)).thenReturn(Collections.emptyList());

        // Act
        List<HouseImageLink> result = houseService.getImageById(id);

        // Assert
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getByHouseImageid(id);
    }

    @Test
    void testGetImageByIdWithEmptyResult2() {
        // Arrange
        int id = -1;

        when(houseRepository.getByHouseImageid(id)).thenReturn(Collections.emptyList());

        // Act
        List<HouseImageLink> result = houseService.getImageById(id);

        // Assert
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).getByHouseImageid(id);
    }

    @Test
    void testGetImageByIdWithEmptyResult3() {
        // Arrange
        int id = 0;

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
    public void testGetHouseById1() {

        // Create a HousesEntity object with an expected ID
        HousesEntity expectedHouse = new HousesEntity();
        expectedHouse.setHouseId(1); // Set the expected ID value

        // Set up the behavior for houseRepository.getHousesEntitiesByHouseId
        when(houseRepository.getHousesEntitiesByHouseId(1)).thenReturn(expectedHouse);

        // Call the method to be tested
        HousesEntity result = houseService.getHouseById(1);

        // Check the result
        assertEquals(expectedHouse, result);

        // Verify that houseRepository.getHousesEntitiesByHouseId was called with the correct parameter
        verify(houseRepository, times(1)).getHousesEntitiesByHouseId(1);
    }

    @Test
    public void testGetHouseById2() {

        // Create a HousesEntity object with an expected ID
        HousesEntity expectedHouse = new HousesEntity();
        expectedHouse.setHouseId(153); // Set the expected ID value

        // Set up the behavior for houseRepository.getHousesEntitiesByHouseId
        when(houseRepository.getHousesEntitiesByHouseId(153)).thenReturn(expectedHouse);

        // Call the method to be tested
        HousesEntity result = houseService.getHouseById(153);

        // Check the result
        assertEquals(expectedHouse, result);

        // Verify that houseRepository.getHousesEntitiesByHouseId was called with the correct parameter
        verify(houseRepository, times(1)).getHousesEntitiesByHouseId(153);
    }

    @Test
    public void testGetHouseById3() {

        // Create a HousesEntity object with an expected ID
        HousesEntity expectedHouse = new HousesEntity();
        expectedHouse.setHouseId(-1); // Set the expected ID value

        // Set up the behavior for houseRepository.getHousesEntitiesByHouseId
        when(houseRepository.getHousesEntitiesByHouseId(-1)).thenReturn(expectedHouse);

        // Call the method to be tested
        HousesEntity result = houseService.getHouseById(-1);

        // Check the result
        assertEquals(expectedHouse, result);

        // Verify that houseRepository.getHousesEntitiesByHouseId was called with the correct parameter
        verify(houseRepository, times(1)).getHousesEntitiesByHouseId(-1);
    }
    @Test
    public void testGetHouseById4() {

        // Create a HousesEntity object with an expected ID
        HousesEntity expectedHouse = new HousesEntity();
        expectedHouse.setHouseId(0); // Set the expected ID value

        // Set up the behavior for houseRepository.getHousesEntitiesByHouseId
        when(houseRepository.getHousesEntitiesByHouseId(0)).thenReturn(expectedHouse);

        // Call the method to be tested
        HousesEntity result = houseService.getHouseById(0);

        // Check the result
        assertEquals(expectedHouse, result);

        // Verify that houseRepository.getHousesEntitiesByHouseId was called with the correct parameter
        verify(houseRepository, times(1)).getHousesEntitiesByHouseId(0);
    }

    @Test
    void testFindHouse1() {

        // Set up test data
        int min1 = 1000000;
        int max1 = 2000000;
        int min2 = 2;
        int max2 = 3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 0;
        int status2 = 0;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse2() {

        // Set up test data
        int min1 = 1000000;
        int max1 = 2000000;
        int min2 = 2;
        int max2 = 3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse3() {

        // Set up test data
        int min1 = 1000000;
        int max1 = 2000000;
        int min2 = 2;
        int max2 = 3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse4() {

        // Set up test data
        int min1 = 1000000;
        int max1 = 2000000;
        int min2 = -2;
        int max2 = -3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 0;
        int status2 = 0;
        String houseName = "house";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse5() {

        // Set up test data
        int min1 = 1000000;
        int max1 = 2000000;
        int min2 = -2;
        int max2 = -2;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 0;
        int status2 = 0;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse6() {

        // Set up test data
        int min1 = 1000000;
        int max1 = 2000000;
        int min2 = -2;
        int max2 = -2;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse7() {

        // Set up test data
        int min1 = 1000000;
        int max1 = 2000000;
        int min2 = -2;
        int max2 = -2;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse8() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = 2000000;
        int max2 = 2000000;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 0;
        int status2 = 0;
        String houseName = "house";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse9() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = 2000000;
        int max2 = 2000000;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 0;
        int status2 = 0;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse10() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = 2000000;
        int max2 = 2000000;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse11() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = 2000000;
        int max2 = 2000000;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse12() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = -2;
        int max2 = -3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 0;
        int status2 = 0;
        String houseName = "house";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse13() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = -2;
        int max2 = -3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 0;
        int status2 = 0;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse14() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = -2;
        int max2 = -3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindHouse15() {

        // Set up test data
        int min1 = -2;
        int max1 = -3;
        int min2 = -2;
        int max2 = -3;
        int province = 1;
        int district = 2;
        int ward = 3;
        int status1 = 1;
        int status2 = 1;
        String houseName = "";
        List<Integer> type = Arrays.asList(1, 2, 3);
        List<Integer> service = Arrays.asList(4, 5, 6);
        int countService = 3;
        int pageIndex = 1;
        int pageSize = 10;

        // Act
        List<Tuple> result = houseRepository.findHouse(min1, max1, min2, max2, province, district, ward, status1, status2, houseName, type, service, countService, pageIndex, pageSize);

        // Assert
        assertNotNull(result);
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
        int status1 = 0;
        int status2 = 0;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse1() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = 2000000;
        int min2 = 2000000;
        int max2 = 4000000;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 0;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testgetAllHouse() {
        HousesEntity housesEntity = new HousesEntity();
        housesEntity.setHouseName("TONY HOUSE");
        List<HousesEntity> houseDtoList = Collections.singletonList(housesEntity);
        when(houseRepository.findAll()).thenReturn(houseDtoList);

        // Act
        List<HousesEntity> result = houseService.getAllHouse();

        // Assert
        assertFalse(result.isEmpty());

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).findAll();
    }

    @Test
    void testViewHouseInHome() {
        List<Tuple> houseDtoList = new ArrayList<>();
        when(houseRepository.viewHouseInHome()).thenReturn(houseDtoList);

        // Act
        List<HouseHomeDto> result = houseService.viewHouseInHome();

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).viewHouseInHome();
    }

    @Test
    void testViewHouseInHomeInFavouriteValid() {
        int id = 1;
        List<Tuple> houseList = new ArrayList<>();
        when(houseRepository.viewHouseInHomeInFavourite(id)).thenReturn(houseList);

        // Act
        List<HouseFavouriteDto> result = houseService.viewHouseInHomeInFavourite(id);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).viewHouseInHomeInFavourite(id);
    }

    @Test
    void testViewHouseInHomeInFavouriteValid2() {
        int id = 2;
        List<Tuple> houseList = new ArrayList<>();
        when(houseRepository.viewHouseInHomeInFavourite(id)).thenReturn(houseList);

        // Act
        List<HouseFavouriteDto> result = houseService.viewHouseInHomeInFavourite(id);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).viewHouseInHomeInFavourite(id);
    }

    @Test
    void testViewHouseInHomeInFavouriteInValid() {
        int id = -1;
        when(houseRepository.viewHouseInHomeInFavourite(id)).thenReturn(null);

        // Act
        try {
            List<HouseFavouriteDto> result = houseService.viewHouseInHomeInFavourite(id);
        } catch (Exception e) {
            // Kiểm tra xem ngoại lệ có phải là NullPointerException hay không
            assertTrue(e instanceof NullPointerException);
        }

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).viewHouseInHomeInFavourite(id);
    }

    @Test
    void testViewHouseInHomeInFavouriteZero() {
        int id = 0;
        when(houseRepository.viewHouseInHomeInFavourite(id)).thenReturn(null);

        // Act
        try {
            List<HouseFavouriteDto> result = houseService.viewHouseInHomeInFavourite(id);
        } catch (Exception e) {
            // Kiểm tra xem ngoại lệ có phải là NullPointerException hay không
            assertTrue(e instanceof NullPointerException);
        }

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).viewHouseInHomeInFavourite(id);
    }

    @Test
    void testGetHouseByRoomIdWithValidId() {
        // Arrange
        int id = 1;

        // Mock data for HousesEntity
        HousesEntity housesEntity = new HousesEntity(/* provide necessary details */);

        when(houseRepository.findHouseByRoomId(id)).thenReturn(housesEntity);

        // Act
        HousesEntity result = houseService.getHouseByRoomId(id);

        // Assert
        assertNotNull(result);
        assertEquals(housesEntity, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByRoomId(id);
    }

    @Test
    void testGetHouseByRoomIdWithInValidId1() {
        // Arrange
        int id = 20;

        // Mock data for HousesEntity
        HousesEntity housesEntity = new HousesEntity(/* provide necessary details */);

        when(houseRepository.findHouseByRoomId(id)).thenReturn(null);

        // Act
        HousesEntity result = houseService.getHouseByRoomId(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByRoomId(id);
    }

    @Test
    void testGetHouseByRoomIdWithInValidId2() {
        // Arrange
        int id = -1;

        // Mock data for HousesEntity
        HousesEntity housesEntity = new HousesEntity(/* provide necessary details */);

        when(houseRepository.findHouseByRoomId(id)).thenReturn(null);

        // Act
        HousesEntity result = houseService.getHouseByRoomId(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByRoomId(id);
    }

    @Test
    void testGetHouseByRoomIdWithInValidId3() {
        // Arrange
        int id = 0;

        // Mock data for HousesEntity
        HousesEntity housesEntity = new HousesEntity(/* provide necessary details */);

        when(houseRepository.findHouseByRoomId(id)).thenReturn(null);

        // Act
        HousesEntity result = houseService.getHouseByRoomId(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByRoomId(id);
    }

    @Test
    void testGetHouseIdByUserIdWithValid() {
        // Arrange
        int id = 1;

        // Mock data for HousesEntity
        List<HousesEntity> housesEntities = new ArrayList<>();

        when(houseRepository.findHouseByMemberId(id)).thenReturn(housesEntities);

        // Act
        List<HousesEntity> result = houseService.getHouseIdByUserId(id);

        // Assert
        assertNotNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByMemberId(id);
    }


    @Test
    void testGetHouseIdByUserIdWithInValid1() {
        // Arrange
        int id = 20;

        // Mock data for HousesEntity
        List<HousesEntity> housesEntities = new ArrayList<>();

        when(houseRepository.findHouseByMemberId(id)).thenReturn(null);

        // Act
        List<HousesEntity> result = houseService.getHouseIdByUserId(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByMemberId(id);
    }

    @Test
    void testGetHouseIdByUserIdWithInValid2() {
        // Arrange
        int id = -1;

        // Mock data for HousesEntity
        List<HousesEntity> housesEntities = new ArrayList<>();

        when(houseRepository.findHouseByMemberId(id)).thenReturn(null);

        // Act
        List<HousesEntity> result = houseService.getHouseIdByUserId(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByMemberId(id);
    }

    @Test
    void testGetHouseIdByUserIdWithInValid3() {
        // Arrange
        int id = 0;

        // Mock data for HousesEntity
        List<HousesEntity> housesEntities = new ArrayList<>();

        when(houseRepository.findHouseByMemberId(id)).thenReturn(null);

        // Act
        List<HousesEntity> result = houseService.getHouseIdByUserId(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findHouseByMemberId(id);
    }

    @Test
    void testCountHouseInAdmin() {
        int a = 1;
        when(houseRepository.countHouses()).thenReturn(a);

        // Act
        int result = houseService.countHousesInAdmin();
        assertEquals(1, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository).countHouses();
    }

    @Test
    void testUpdateStarValid() {
        int star = 1;
        int houseId = 1;

        // Call the method with input value 1
        houseService.updateStar(star, houseId);

        // Verify that updateStatusToTrue is called with the correct argument
        verify(houseRepository, times(1)).updateStarHouse(star, houseId);
    }

    @Test
    void testUpdateStarInValid1() {
        HouseRepository houseRepository = mock(HouseRepository.class);
        int star = -1;
        int houseId = 1;

        // Call the method with input value 1
        houseService.updateStar(star, houseId);

        // Verify that updateStatusToTrue is called with the correct argument
        verify(houseRepository, never()).updateStarHouse(star, houseId);
    }

    @Test
    void testUpdateStarInValid2() {
        HouseRepository houseRepository = mock(HouseRepository.class);
        int star = 1;
        int houseId = -1;

        // Call the method with input value 1
        houseService.updateStar(star, houseId);

        // Verify that updateStatusToTrue is called with the correct argument
        verify(houseRepository, never()).updateStarHouse(star, houseId);
    }

    @Test
    void testUpdateStarInValid3() {
        HouseRepository houseRepository = mock(HouseRepository.class);
        int star = -1;
        int houseId = -1;

        // Call the method with input value 1
        houseService.updateStar(star, houseId);

        // Verify that updateStatusToTrue is called with the correct argument
        verify(houseRepository, never()).updateStarHouse(star, houseId);
    }

    @Test
    void testFindHouseById() {
        int id = 1;
        HousesEntity housesEntity = new HousesEntity();

        // Mock data for HousesEntity
        Optional<HousesEntity> housesEntityOptional = Optional.ofNullable(housesEntity);


        when(houseRepository.findById(id)).thenReturn(housesEntityOptional);

        // Act
        Optional<HousesEntity> result = houseService.findHouseById(id);

        // Assert
        assertNotNull(result);
        assertEquals(housesEntityOptional, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findById(id);
    }

    @Test
    void testFindHouseByIdInvalid1() {
        int id = 200;
        HousesEntity housesEntity = new HousesEntity();

        // Mock data for HousesEntity
        Optional<HousesEntity> housesEntityOptional = null;


        when(houseRepository.findById(id)).thenReturn(housesEntityOptional);

        // Act
        Optional<HousesEntity> result = houseService.findHouseById(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findById(id);
    }

    @Test
    void testFindHouseByIdInvalid2() {
        int id = 0;
        HousesEntity housesEntity = new HousesEntity();

        // Mock data for HousesEntity
        Optional<HousesEntity> housesEntityOptional = null;


        when(houseRepository.findById(id)).thenReturn(housesEntityOptional);

        // Act
        Optional<HousesEntity> result = houseService.findHouseById(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findById(id);
    }

    @Test
    void testFindHouseByIdInvalid3() {
        int id = -1;
        HousesEntity housesEntity = new HousesEntity();

        // Mock data for HousesEntity
        Optional<HousesEntity> housesEntityOptional = null;


        when(houseRepository.findById(id)).thenReturn(housesEntityOptional);

        // Act
        Optional<HousesEntity> result = houseService.findHouseById(id);

        // Assert
        assertNull(result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).findById(id);
    }


    @Test
    void testCountHouse2() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = 2000000;
        int min2 = 2000000;
        int max2 = 4000000;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse3() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = 2000000;
        int min2 = 2000000;
        int max2 = 4000000;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse4() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = -2;
        int min2 = 0;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 0;
        int status2 = 0;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse5() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = -2;
        int min2 = 0;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 0;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse6() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = -2;
        int min2 = 0;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse7() {
        // Arrange
        int min1 = 0; // Assuming id with no data
        int max1 = -2;
        int min2 = 0;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse8() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = 0;
        int min2 = -2;
        int max2 = 0;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 0;
        int status2 = 0;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse9() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = 0;
        int min2 = -2;
        int max2 = 0;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 0;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse10() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = 0;
        int min2 = -2;
        int max2 = 0;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse11() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = 0;
        int min2 = -2;
        int max2 = 0;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse12() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = -2;
        int min2 = -2;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 0;
        int status2 = 0;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse13() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = -2;
        int min2 = -2;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 0;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse14() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = -2;
        int min2 = -2;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
        String houseName = "house";
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

    @Test
    void testCountHouse15() {
        // Arrange
        int min1 = -2; // Assuming id with no data
        int max1 = -2;
        int min2 = -2;
        int max2 = -2;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status1 = 1;
        int status2 = 1;
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
                province, district, ward, status1, status2, houseName, type, service, countService)).thenReturn(countHouse);

        // Act
        int result = houseService.countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);

        // Assert
        assertNotNull(result);
        assertEquals(countHouse, result);

        // Verify that the repository method was called with the correct parameter
        verify(houseRepository, times(1)).countHouse(min1, max1, min2, max2,
                province, district, ward, status1, status2, houseName, type, service, countService);
    }

}
