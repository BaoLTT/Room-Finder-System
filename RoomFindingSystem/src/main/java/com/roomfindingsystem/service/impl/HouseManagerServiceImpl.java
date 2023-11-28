package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.entity.ServiceHouseEntity;
import com.roomfindingsystem.repository.HouseManagerRepository;
import com.roomfindingsystem.repository.ImagesHouseRepository;
import com.roomfindingsystem.repository.ServiceHouseRepository;
import com.roomfindingsystem.service.HouseManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    @Autowired
    ServiceHouseRepository serviceHouseRepository;

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
    public void insertHouse(HouseLandlordVo house,int addressID) {
        LocalDate createdDate = LocalDate.now();
        HousesEntity housesEntity = new HousesEntity();
        housesEntity.setHouseName(house.getHouseName());
        housesEntity.setDescription(house.getDescription());
        housesEntity.setCreatedDate(createdDate);
        housesEntity.setCreatedBy(1);
        housesEntity.setTypeHouseId(house.getTypeHouseID());
        housesEntity.setStatus(house.getStatus());
        housesEntity.setAddressId(addressID);
        housesEntity.setUserId(house.getUserID());
        housesEntity.setLastModifiedBy(1);
        housesEntity.setLastModifiedDate(createdDate);
        houseManagerRepository.save(housesEntity);
        for(int i =0; i<house.getService().size();i++){
            ServiceHouseEntity serviceHouseEntity = new ServiceHouseEntity();
            serviceHouseEntity.setHouseId( housesEntity.getHouseId());
            int serviceid = Integer.parseInt(house.getService().get(i));
            serviceHouseEntity.setServiceId(serviceid);

            serviceHouseRepository.save(serviceHouseEntity);
        }
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
    public void updateHouse(HouseLandlordVo houses, int houseID,List<Integer> service) {
        LocalDate localDate = LocalDate.now();
        houseManagerRepository.updateHouse(houses.getHouseName(), houses.getTypeHouseID(),houses.getDescription(),1,localDate,houses.getStatus(),houseID);
        serviceHouseRepository.deleteByHouseId(houseID);
        if(!service.contains(0)){
            for(int i =0; i<service.size();i++){
                ServiceHouseEntity serviceHouseEntity = new ServiceHouseEntity();
                serviceHouseEntity.setHouseId( houseID);
                int serviceid = service.get(i);
                serviceHouseEntity.setServiceId(serviceid);
                serviceHouseRepository.save(serviceHouseEntity);

            }
        }
    }

}
