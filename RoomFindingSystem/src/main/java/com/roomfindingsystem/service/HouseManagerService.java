package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("houseManagerService")
public interface HouseManagerService {
    List<HouseManagerTypeVo> findHouseManager();
    boolean deleteHouse(Integer id);

    HouseManagerTypeVo findHouseById(Integer id);

    void insertHouse(HouseLandlordVo house,int addressID, MultipartFile[] files) throws IOException;

    HousesEntity getLastHouse();


    void updateHouse(HouseLandlordVo houses, int houseID,List<Integer> service,MultipartFile[] files ) throws IOException;

}
