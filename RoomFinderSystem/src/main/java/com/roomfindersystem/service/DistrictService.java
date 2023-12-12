package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.DistrictEntity;

import java.util.List;

public interface DistrictService {
    List<DistrictEntity> getAll();
    List<DistrictEntity> getDistrictsByProvince(Integer provinceId);
}
