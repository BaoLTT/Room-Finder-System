package com.roomfindersystem.service;

import com.roomfindersystem.entity.WardEntity;

import java.util.List;

public interface WardService {
    List<WardEntity> getAll();
    List<WardEntity> getWardsByDistrictAndProvince(Integer districtId, Integer provinceId);
}
