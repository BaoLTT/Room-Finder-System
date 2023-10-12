package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.reponsitory.HouseReponsitory;
import com.roomfindingsystem.vo.HouseTypeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("houseService")
public class HouseServiceImpl implements HouseService {
    private HouseReponsitory houseReponsitory;

    public HouseServiceImpl(HouseReponsitory houseReponsitory){
        super();
        this.houseReponsitory = houseReponsitory;
    }


    @Override
    public List<HouseTypeVo> getAllHouse() {
        return houseReponsitory.getAllHouse();
    }

    @Override
    public Page<HouseTypeVo> findHouse(int min,int max,String houseName,List<Integer> type, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        return houseReponsitory.findHouse(min, max ,"%" + houseName + "%",type,pageable);
    }


}
