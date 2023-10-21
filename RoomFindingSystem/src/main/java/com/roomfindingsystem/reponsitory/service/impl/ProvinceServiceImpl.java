package com.roomfindingsystem.reponsitory.service.impl;

import com.roomfindingsystem.entity.ProvinceEntity;
import com.roomfindingsystem.reponsitory.ProvinceRepository;
import com.roomfindingsystem.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    private final ModelMapper modelMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ModelMapper modelMapper) {
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProvinceEntity> getAll() {
        List<ProvinceEntity> provinces = provinceRepository.findAll();
        return provinces;
    }
}
