package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.vo.HouseManagerTypeVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("houseManagerService")
public interface HouseManagerService {
    List<HouseManagerTypeVo> findHouseManager();
    boolean deleteHouse(Integer id);

    HouseManagerTypeVo findHouseById(Integer id);

    void insertHouse(HousesEntity house);

    HousesEntity getLastHouse();

    void inserImageHouse(HouseImagesEntity images);

}
