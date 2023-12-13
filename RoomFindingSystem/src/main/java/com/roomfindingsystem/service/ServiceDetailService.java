package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import java.util.List;

public interface ServiceDetailService {
    ServiceDto findByName(String name);

    List<ServiceDetailEntity> getAllService();

    List<ServiceDetailEntity> getServiceNotUse();

    void save(ServiceDetailEntity serviceDetailEntity);

    void delete(Integer serviceid);

    List<ServiceDetailEntity> getServiceExceptHouseService(int houseId);
}
