package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.HousesEntity;

import com.roomfindingsystem.entity.TypeHouseEntity;

import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.ServiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    //Th
    int countHouse(int min, int max, String houseName, List<Integer> type);

    List<HouseTypeVo> findHouse(int min, int max,String houseName,List<Integer> type, int pageIndex,int pageSize);
    //
    List<HouseTypeVo> viewHouseInHome();
    Optional<HousesEntity> findHouseById(Integer id);
    List<HouseDto> getHouseDetail(int id);
    List<HouseImageLink> getImageById(int id);
    List<ServiceDto> getServiceById(int id);
    HousesEntity getHouseByRoomId(int roomId);
}
