package com.roomfindersystem.service;

import com.roomfindersystem.dto.ServiceDto;
import com.roomfindersystem.entity.ServiceDetailEntity;

import java.util.List;

public interface ServiceDetailService {
    ServiceDto findByName(String name);

    List<ServiceDetailEntity> getAllService();

    void save(ServiceDetailEntity serviceDetailEntity);

    List<ServiceDetailEntity> getServiceExceptHouseService(int houseId);
}
