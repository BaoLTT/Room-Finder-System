package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.WardEntity;
import com.roomfindingsystem.reponsitory.WardRepository;
import com.roomfindingsystem.service.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    private final ModelMapper modelMapper;

    public WardServiceImpl(WardRepository wardRepository, ModelMapper modelMapper) {
        this.wardRepository = wardRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<WardEntity> getAll() {
        List<WardEntity> wards = wardRepository.findAll();
        return wards;
    }

    @Override
    public List<WardEntity> getWardsByDistrictAndProvince(Integer districtId, Integer provinceId) {
        return wardRepository.findByDistrictIdAndProvinceId(districtId, provinceId);
    }
}
