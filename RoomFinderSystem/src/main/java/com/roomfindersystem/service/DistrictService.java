package com.roomfindersystem.service;

import com.roomfindersystem.entity.DistrictEntity;

import java.util.List;

public interface DistrictService {
    List<DistrictEntity> getAll();
    List<DistrictEntity> getDistrictsByProvince(Integer provinceId);
}
