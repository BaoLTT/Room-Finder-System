package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.repository.ServiceDetailRepository;
import com.roomfindingsystem.service.ServiceDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
