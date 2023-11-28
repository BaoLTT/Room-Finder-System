package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.HouseLandlordVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HouseLandlordService {

    HouseLandlordVo findHouseByID(int houseid);
}
