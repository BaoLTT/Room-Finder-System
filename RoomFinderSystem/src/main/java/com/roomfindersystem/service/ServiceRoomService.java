package com.roomfindersystem.service;

import com.roomfindersystem.dto.ServiceDto;

import java.util.List;

public interface ServiceRoomService {
    List<ServiceDto> findAll();
}
