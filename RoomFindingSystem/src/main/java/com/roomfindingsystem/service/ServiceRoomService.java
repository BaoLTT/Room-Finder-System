package com.roomfindingsystem.service;

import com.roomfindingsystem.vo.ServiceDto;

import java.util.List;

public interface ServiceRoomService {
    List<ServiceDto> findAll();
}
