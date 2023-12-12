package com.roomfindersystem.service;

import com.roomfindersystem.dto.HouseLandlordVo;
import com.roomfindersystem.dto.HouseManagerTypeVo;
import com.roomfindersystem.entity.HouseImagesEntity;
import com.roomfindersystem.entity.HousesEntity;
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

    void deleteImageById(int imageId);

    void updateHouse(HouseLandlordVo houses, int houseID,List<Integer> service,MultipartFile[] files ) throws IOException;

}
