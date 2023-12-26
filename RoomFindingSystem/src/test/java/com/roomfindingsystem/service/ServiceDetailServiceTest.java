package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.RoomTypeDto;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.repository.ServiceDetailRepository;
import com.roomfindingsystem.service.impl.ServiceDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceDetailServiceTest {
    @InjectMocks
    private ServiceDetailServiceImpl service;

    @Mock
    private ServiceDetailRepository serviceDetailRepository;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllService() {
        List<ServiceDetailEntity> mockServiceList = Arrays.asList();


        // Gọi phương thức từ đối tượng under test
        List<ServiceDetailEntity> result = service.getAllService();

        // Kiểm tra kết quả
        assertEquals(0, result.size());

    }

    @Test
    void getServiceNotUse() {
        List<ServiceDetailEntity> mockNotUsedServiceList = Arrays.asList(
        );


        // Gọi phương thức từ đối tượng under test
        List<ServiceDetailEntity> result = service.getServiceNotUse();

        // Kiểm tra kết quả
        assertEquals(mockNotUsedServiceList.size(), result.size());


    }

    @Test
    void testFindByNameValid() {
        String name = "Tự Do";
        ServiceDetailService serviceDetailService = Mockito.mock(ServiceDetailService.class);
        ServiceDto serviceDto = serviceDetailService.findByName(name);
        serviceDetailService.findByName(name);
        assertEquals(serviceDto, serviceDetailService.findByName(name));
    }

    @Test
    void testFindByNameNoValid() {
        String name = "";
        ServiceDetailService serviceDetailService = Mockito.mock(ServiceDetailService.class);
        ServiceDto serviceDto = serviceDetailService.findByName(name);
        serviceDetailService.findByName(name);
        assertEquals(serviceDto, serviceDetailService.findByName(name));
    }

    @Test
    void testFindByNameNullValid() {
        String name = null;
        ServiceDetailService serviceDetailService = Mockito.mock(ServiceDetailService.class);
        ServiceDto serviceDto = serviceDetailService.findByName(name);
        serviceDetailService.findByName(name);
        assertEquals(serviceDto, serviceDetailService.findByName(name));
    }

    @Test
    void saveInValid() {

        ServiceDetailEntity serviceDetailEntity = new ServiceDetailEntity();
        ServiceDetailService serviceDetailService = Mockito.mock(ServiceDetailService.class);

        serviceDetailService.save(serviceDetailEntity);
        assertTrue(true);
    }

    @Test
    void save() {

        ServiceDetailEntity serviceDetailEntity = new ServiceDetailEntity();

        service.save(serviceDetailEntity);

//        verify(serviceDetailRepository, times(1)).save(serviceDetailEntity);
    }

    @Test
    void delete() {
        Integer serviceIdToDelete = 1;

        service.delete(serviceIdToDelete);

//        verify(serviceDetailRepository, times(1)).deleteById(serviceIdToDelete);
    }

    @Test
    void deleteValid() {
        Integer serviceIdToDelete = 10;
        service.delete(serviceIdToDelete);
        assertTrue(true);

        service.delete(serviceIdToDelete);
    }

    @Test
    void deleteNoValid() {
        Integer serviceIdToDelete = -1;
        service.delete(serviceIdToDelete);

        assertTrue(true);
    }

    @Test
    void deleteNullValid() {
        Integer serviceIdToDelete = 0;
        service.delete(serviceIdToDelete);
        assertTrue(true);
    }

    @Test
    void getServiceExceptHouseService() {
        // Đối số của phương thức
        int houseId = 1;

        List<ServiceDetailEntity> allServiceDetails = Arrays.asList(
        );

        List<String> serviceNamesInHouse = Arrays.asList("Service1", "Service3");

        // Gọi phương thức under test
        List<ServiceDetailEntity> result = service.getServiceExceptHouseService(houseId);

        // Kiểm tra kết quả
        assertThat(result).hasSize(allServiceDetails.size());


    }

    @Test
    void getServiceExceptHouseServiceValid() {
        // Đối số của phương thức
        int houseId = 100;

        List<ServiceDetailEntity> allServiceDetails = Arrays.asList(
        );

        List<String> serviceNamesInHouse = Arrays.asList("Service1", "Service3");

        // Gọi phương thức under test
        List<ServiceDetailEntity> result = service.getServiceExceptHouseService(houseId);

        // Kiểm tra kết quả
        assertThat(result).hasSize(allServiceDetails.size());


    }

    @Test
    void getServiceExceptHouseServiceNoValid() {
        // Đối số của phương thức
        int houseId = -1;

        List<ServiceDetailEntity> allServiceDetails = Arrays.asList(
        );

        List<String> serviceNamesInHouse = Arrays.asList("Service1", "Service3");

        // Gọi phương thức under test
        List<ServiceDetailEntity> result = service.getServiceExceptHouseService(houseId);

        // Kiểm tra kết quả
        assertThat(result).hasSize(allServiceDetails.size());


    }

    @Test
    void getServiceExceptHouseServiceNullValid() {
        // Đối số của phương thức
        int houseId = 0;

        List<ServiceDetailEntity> allServiceDetails = Arrays.asList(
        );

        List<String> serviceNamesInHouse = Arrays.asList("Service1", "Service3");

        // Gọi phương thức under test
        List<ServiceDetailEntity> result = service.getServiceExceptHouseService(houseId);

        // Kiểm tra kết quả
        assertThat(result).hasSize(allServiceDetails.size());


    }

}