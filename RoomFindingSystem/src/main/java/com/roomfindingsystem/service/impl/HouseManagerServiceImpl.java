package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.reponsitory.HouseManagerReponsitory;
import com.roomfindingsystem.reponsitory.ImagesHouseRepository;
import com.roomfindingsystem.service.HouseManagerService;
import com.roomfindingsystem.vo.HouseManagerTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("houseManagerService")
public class HouseManagerServiceImpl implements HouseManagerService {
    private HouseManagerReponsitory houseManagerReponsitory;

    public HouseManagerServiceImpl(HouseManagerReponsitory houseManagerReponsitory){
        super();
        this.houseManagerReponsitory = houseManagerReponsitory;
    }
    @Autowired
    ImagesHouseRepository imagesHouseRepository;

    @Override
    public List<HouseManagerTypeVo> findHouseManager() {
        return houseManagerReponsitory.findHouseManager();
    }

    @Override
    public boolean deleteHouse(Integer id) {
        if ( houseManagerReponsitory.findById(id).isEmpty()) {
            System.err.println("House with id: "+ id +" not found!");
        }
        houseManagerReponsitory.deleteById(id);
        return true;
    }

    @Override
    public HouseManagerTypeVo findHouseById(Integer id) {
        HouseManagerTypeVo house = houseManagerReponsitory.findHouseById(id);
        return house;
    }

    @Override
    public void insertHouse(HousesEntity house) {
        houseManagerReponsitory.save(house);
    }

    @Override
    public HousesEntity getLastHouse() {
        return houseManagerReponsitory.getLastHouse();
    }

    @Override
    public void inserImageHouse(HouseImagesEntity images) {
        imagesHouseRepository.save(images);
    }

}
