package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.reponsitory.ServiceRoomRepository;
import com.roomfindingsystem.service.ServiceRoomService;
import com.roomfindingsystem.dto.ServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRoomServiceImpl implements ServiceRoomService {
    private final ServiceRoomRepository serviceRoomRepository;
    private final ModelMapper modelMapper;

    public ServiceRoomServiceImpl(ServiceRoomRepository serviceRoomRepository, ModelMapper modelMapper) {
        this.serviceRoomRepository = serviceRoomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceDto> findAll() {
        return null;
    }
}
