package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import java.util.List;

public interface ServiceDetailService {
    ServiceDto findByName(String name);

    List<ServiceDetailEntity> getAllService();

    void save(ServiceDetailEntity serviceDetailEntity);

    List<ServiceDetailEntity> getServiceExceptHouseService(int houseId);
}
