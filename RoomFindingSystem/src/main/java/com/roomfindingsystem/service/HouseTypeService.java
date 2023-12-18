package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.TypeHouseEntity;

import java.util.List;

public interface HouseTypeService {
    List<TypeHouseEntity> findAll();
    List<TypeHouseEntity> findTypeNotUse();

    void deleteType(Integer typeid);

    void addType(TypeHouseEntity typeHouseEntity);
}
