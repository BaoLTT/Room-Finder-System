package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("houseManagerService")
public interface HouseManagerService {
    List<HouseManagerTypeVo> findHouseManager();
    boolean deleteHouse(Integer id);

    HouseManagerTypeVo findHouseById(Integer id);


    HousesEntity getLastHouse();



}
