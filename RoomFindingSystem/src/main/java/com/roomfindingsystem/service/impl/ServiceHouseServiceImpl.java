package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.repository.ServiceHouseRepository;
import com.roomfindingsystem.service.ServiceHouseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("serviceHouseService")
public class ServiceHouseServiceImpl implements ServiceHouseService {
    private ServiceHouseRepository serviceHouseRepository;

    public ServiceHouseServiceImpl(ServiceHouseRepository serviceHouseRepository){
        super();
        this.serviceHouseRepository = serviceHouseRepository;
    }
    @Override
    public List<ServiceDetailEntity> findAll() {
        return serviceHouseRepository.findAll();
    }
}
