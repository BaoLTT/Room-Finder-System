package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.HouseLandlordVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HouseLandlordService {
    List<HouseLandlordVo> findHouseByUser(int userId);
    List<HouseLandlordVo> getAllHouse();

    void deleteImageById(int imageId);

    HouseLandlordVo findHouseByID(int houseid);
}
