package com.roomfindersystem.service.impl;

import com.roomfindersystem.entity.DistrictEntity;
import com.roomfindersystem.repository.DistrictRepository;
import com.roomfindersystem.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    private final ModelMapper modelMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DistrictEntity> getAll() {
        List<DistrictEntity> districts = districtRepository.findAll();
        return districts;
    }
    @Override
    public List<DistrictEntity> getDistrictsByProvince(Integer provinceId) {
        System.out.println(districtRepository.findByProvinceId(provinceId));
        return districtRepository.findByProvinceId(provinceId);
    }
}
