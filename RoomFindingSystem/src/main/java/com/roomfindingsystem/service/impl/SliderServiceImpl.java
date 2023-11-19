package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.SliderEntity;
import com.roomfindingsystem.repository.SliderRepository;
import com.roomfindingsystem.service.SliderService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
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

    @Override
    public SliderEntity getSliderById(int sliderId) {
        return sliderRepository.getSliderEntitiesBySliderId(sliderId);
    }

    @Override
    public SliderEntity save(SliderEntity sliderEntity) {
        return sliderRepository.save(sliderEntity);
    }

    @Override
    public SliderEntity update(SliderEntity sliderEntity)  {
        SliderEntity slider = sliderRepository.findById(sliderEntity.getSliderid()).get();
        SliderEntity slider1 = new SliderEntity();
        slider1.setSliderid(slider.getSliderid());
        slider1.setTitle(sliderEntity.getTitle());
        slider1.setContent(sliderEntity.getContent());
        slider1.setStatus(sliderEntity.getStatus());
        slider1.setCreatedDate(slider.getCreatedDate());
        slider1.setLastModifiedDate(LocalDate.now());
        slider1.setCreatedBy(slider.getCreatedBy());
        slider1.setRoomid(slider.getRoomid());
        slider1.setImgLink(sliderEntity.getImgLink());
        return sliderRepository.save(slider1);
    }

    @Override
    public void deleteById(int id) {
         sliderRepository.deleteById(id);
    }


}
