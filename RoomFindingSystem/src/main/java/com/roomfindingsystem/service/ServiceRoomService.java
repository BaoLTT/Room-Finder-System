package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.ServiceDto;

import java.util.List;

public interface ServiceRoomService {
    List<ServiceDto> findAll();
}
