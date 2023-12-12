package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.TypeHouseEntity;

import java.util.List;

public interface HouseTypeService {
    List<TypeHouseEntity> findAll();

    void addType(TypeHouseEntity typeHouseEntity);
}
