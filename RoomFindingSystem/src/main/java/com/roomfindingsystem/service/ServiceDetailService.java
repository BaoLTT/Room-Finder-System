package com.roomfindingsystem.service;

import com.roomfindingsystem.vo.ServiceDto;

public interface ServiceDetailService {
    ServiceDto findByName(String name);
}
