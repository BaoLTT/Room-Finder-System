package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.WardEntity;

import java.util.List;

public interface WardService {
    List<WardEntity> getAll();
    List<WardEntity> getWardsByDistrictAndProvince(Integer districtId, Integer provinceId);
}
