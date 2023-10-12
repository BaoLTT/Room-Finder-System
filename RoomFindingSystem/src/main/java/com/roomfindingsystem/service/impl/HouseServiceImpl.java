package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.reponsitory.HouseReponsitory;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.HouseTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseReponsitory houseResponsitory;




    @Override
    public List<HouseTypeVo> getAllHouse() {
        return houseResponsitory.getAllHouse();
    }

    @Override
    public Page<HouseTypeVo> findHouse(int min,int max,String houseName,List<Integer> type, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        return houseResponsitory.findHouse(min, max ,"%" + houseName + "%",type,pageable);
    }


    @Override
    public Optional<HousesEntity> findHouseById(Integer id) {
        return houseResponsitory.findById(id);
    }

    @Override
    public List<HouseDto> getHouseDetail(int id) {
        return houseResponsitory.findAllDetail();
    }

    @Override
    public List<HouseImageLink> getImageById(int id) {
        return  houseResponsitory.getByHouseImageid(id);
    }


}
