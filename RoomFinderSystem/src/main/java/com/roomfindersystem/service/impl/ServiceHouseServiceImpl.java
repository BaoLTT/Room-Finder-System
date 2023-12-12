package com.roomfindersystem.service.impl;

import com.roomfindersystem.entity.ServiceDetailEntity;
import com.roomfindersystem.repository.ServiceHouseRepository;
import com.roomfindersystem.service.ServiceHouseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("serviceHouseService")
public class ServiceHouseServiceImpl implements ServiceHouseService {
    private ServiceHouseRepository serviceHouseRepository;

    public ServiceHouseServiceImpl(ServiceHouseRepository serviceHouseRepository){
        super();
        this.serviceHouseRepository = serviceHouseRepository;
    }
}
