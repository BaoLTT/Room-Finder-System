package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.reponsitory.ServiceDetailRepository;
import com.roomfindingsystem.service.ServiceDetailService;
import com.roomfindingsystem.dto.ServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
