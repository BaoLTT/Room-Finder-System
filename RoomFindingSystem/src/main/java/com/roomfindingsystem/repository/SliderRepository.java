package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.SliderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sliderRepository")
public interface SliderRepository extends JpaRepository<SliderEntity, Integer> {
    List<SliderEntity> findAll();


}
