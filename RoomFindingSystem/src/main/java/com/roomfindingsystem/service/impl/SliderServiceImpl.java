package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.SliderEntity;
import com.roomfindingsystem.repository.SliderRepository;
import com.roomfindingsystem.service.SliderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("sliderService")
public class SliderServiceImpl implements SliderService {
    private SliderRepository sliderRepository;

    public SliderServiceImpl(SliderRepository sliderRepository){
        super();
        this.sliderRepository = sliderRepository;
    }
    @Override
    public List<SliderEntity> viewTop4Home() {
        return sliderRepository.findAll();
    }
}
