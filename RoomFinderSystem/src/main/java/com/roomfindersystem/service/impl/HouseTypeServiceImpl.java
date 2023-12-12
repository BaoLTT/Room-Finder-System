package com.roomfindersystem.service.impl;

import com.roomfindersystem.entity.TypeHouseEntity;
import com.roomfindersystem.repository.HouseTypeRepository;
import com.roomfindersystem.service.HouseTypeService;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("houseTypeService")
public class HouseTypeServiceImpl implements HouseTypeService {
    private HouseTypeRepository houseTypeRepository;

    public HouseTypeServiceImpl(HouseTypeRepository houseTypeRepository){
        super();
        this.houseTypeRepository = houseTypeRepository;
    }
    @Override
    public List<TypeHouseEntity> findAll() {
        return  houseTypeRepository.findAll();
    }

    @Override
    public void addType(TypeHouseEntity typeHouseEntity) {
        houseTypeRepository.save(typeHouseEntity);
    }
}
