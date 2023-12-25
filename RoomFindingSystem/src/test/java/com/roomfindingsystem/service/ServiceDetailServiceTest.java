package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.repository.ServiceDetailRepository;
import com.roomfindingsystem.service.impl.ServiceDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
    void findByName() {

    }
}