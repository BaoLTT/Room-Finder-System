package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.repository.HouseManagerRepository;
import com.roomfindingsystem.repository.ImagesHouseRepository;
import com.roomfindingsystem.service.HouseManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("houseManagerService")
public class HouseManagerServiceImpl implements HouseManagerService {
    private HouseManagerRepository houseManagerRepository;

    public HouseManagerServiceImpl(HouseManagerRepository houseManagerRepository){
        super();
        this.houseManagerRepository = houseManagerRepository;
    }
    @Autowired
    ImagesHouseRepository imagesHouseRepository;

    @Override
    public List<HouseManagerTypeVo> findHouseManager() {
        return houseManagerRepository.findHouseManager();
    }

    @Override
    public boolean deleteHouse(Integer id) {
        if ( houseManagerRepository.findById(id).isEmpty()) {
            System.err.println("House with id: "+ id +" not found!");
        }
        houseManagerRepository.deleteById(id);
        return true;
    }

    @Override
    public HouseManagerTypeVo findHouseById(Integer id) {
        HouseManagerTypeVo house = houseManagerRepository.findHouseById(id);
        return house;
    }

    @Override
    public void insertHouse(HousesEntity house) {
        houseManagerRepository.save(house);
    }

    @Override
    public HousesEntity getLastHouse() {
        return houseManagerRepository.getLastHouse();
    }

    @Override
    public void inserImageHouse(HouseImagesEntity images) {
        imagesHouseRepository.save(images);
    }

    @Transactional
    @Override
    public void updateHouse(HousesEntity houses, int houseID) {
        houseManagerRepository.updateHouse(houses.getHouseName(), houses.getTypeHouseId(),houses.getAddressId(),houses.getDescription(),houses.getLastModifiedBy(),houses.getLastModifiedDate(),houseID);
    }
}
