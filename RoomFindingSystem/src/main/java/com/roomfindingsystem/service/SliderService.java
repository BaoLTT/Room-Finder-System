package com.roomfindingsystem.service;


import com.roomfindingsystem.entity.SliderEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface SliderService {
    List<SliderEntity> viewTop7Home();

    List<SliderEntity> viewAll();

    SliderEntity getSliderById(int sliderId);

    SliderEntity save(SliderEntity sliderEntity) ;

    SliderEntity update(SliderEntity sliderEntity);


}
