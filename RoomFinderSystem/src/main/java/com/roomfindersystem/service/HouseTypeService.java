package com.roomfindersystem.service;

import com.roomfindersystem.entity.TypeHouseEntity;

import java.util.List;

public interface HouseTypeService {
    List<TypeHouseEntity> findAll();

    void addType(TypeHouseEntity typeHouseEntity);
}
