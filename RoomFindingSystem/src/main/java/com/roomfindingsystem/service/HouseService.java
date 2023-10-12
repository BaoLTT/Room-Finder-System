package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.vo.HouseTypeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HouseService {
//    List<House1ListEntity> getAllHouse();

    List<HouseTypeVo> getAllHouse();

    Page<HouseTypeVo> findHouse(int min, int max,String houseName,List<Integer> type, int pageIndex,int pageSize);


}
