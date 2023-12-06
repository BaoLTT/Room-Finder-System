package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.repository.ServiceDetailRepository;
import com.roomfindingsystem.service.ServiceDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceDetailServiceImpl implements ServiceDetailService {
    private final ServiceDetailRepository serviceDetailRepository;
    private final ModelMapper modelMapper;


    public ServiceDetailServiceImpl(ServiceDetailRepository serviceDetailRepository, ModelMapper modelMapper) {
        this.serviceDetailRepository = serviceDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceDto findByName(String name) {
        ServiceDetailEntity serviceDetailEntity = serviceDetailRepository.findByServiceName(name).get();
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setServiceId(serviceDetailEntity.getServiceId());
        serviceDto.setServiceName(serviceDetailEntity.getServiceName());
        return serviceDto;
    }

    @Override
    public List<ServiceDetailEntity> getAllService() {
        return serviceDetailRepository.findAll();
    }



    @Override
    public void save(ServiceDetailEntity serviceDetailEntity) {
        serviceDetailRepository.save(serviceDetailEntity);
    }

    @Override
    public List<ServiceDetailEntity> getServiceExceptHouseService(int houseId) {
        List<ServiceDetailEntity> entityList = serviceDetailRepository.findAll();
        List<String> stringList = serviceDetailRepository.getServiceNameByHouseId(houseId);

        List<ServiceDetailEntity> filteredEntities = entityList.stream()
                                .filter(entity -> !stringList.contains(entity.getServiceName()))
                                .toList();
        return filteredEntities;
    }
}
