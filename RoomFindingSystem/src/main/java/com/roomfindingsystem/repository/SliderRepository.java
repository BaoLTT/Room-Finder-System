package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.SliderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("sliderRepository")
public interface SliderRepository extends JpaRepository<SliderEntity, Integer> {
    List<SliderEntity> findAll();

    @Query
    SliderEntity getSliderEntitiesBySliderId(int sliderId);
//    @Query
//    SliderEntity save();
    @Query("select count(*) from SliderEntity ")
    int countSliders();

}
