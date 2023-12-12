package com.roomfindersystem.service.impl;

import com.roomfindersystem.repository.ServiceRoomRepository;
import com.roomfindersystem.service.ServiceRoomService;
import com.roomfindersystem.dto.ServiceDto;
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
