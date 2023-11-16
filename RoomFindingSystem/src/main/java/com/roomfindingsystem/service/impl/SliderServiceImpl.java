package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.SliderEntity;
import com.roomfindingsystem.repository.SliderRepository;
import com.roomfindingsystem.service.SliderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("sliderService")
public class SliderServiceImpl implements SliderService {
    private SliderRepository sliderRepository;

    public SliderServiceImpl(SliderRepository sliderRepository){
        super();
        this.sliderRepository = sliderRepository;
    }
    @Override
    public List<SliderEntity> viewTop7Home() {
        List<SliderEntity> list = new ArrayList<>();
        if(sliderRepository.findAll().size()<7){
            return sliderRepository.findAll();
        } else {
            for(int i = 0; i<7; i++){
                list.add(sliderRepository.findAll().get(i));
            }
            return list;
        }

    }

    @Override
    public List<SliderEntity> viewAll() {
        return sliderRepository.findAll();
    }


}
