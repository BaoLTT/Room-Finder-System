package com.roomfindingsystem.reponsitory.service;

import com.roomfindingsystem.entity.WardEntity;

import java.util.List;

public interface WardService {
    List<WardEntity> getAll();
    List<WardEntity> getWardsByDistrict(Integer districtId);
}
