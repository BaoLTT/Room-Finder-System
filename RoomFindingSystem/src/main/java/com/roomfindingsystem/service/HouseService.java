package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.entity.HousesEntity;

import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.ServiceDto;
import java.util.List;
import java.util.Optional;

public interface HouseService {
    //Th
    int countHouse(int min, int max, String houseName, List<Integer> type, List<Integer> service);

    List<HouseTypeVo> findHouse(int min, int max,String houseName,List<Integer> type, List<Integer> service, int pageIndex,int pageSize);
    //
    List<HouseTypeVo> viewHouseInHome();

    List<HouseHomeDto> viewHouseInHome();
    Optional<HousesEntity> findHouseById(Integer id);
    List<HouseDto> getHouseDetail(int id);
    List<HouseImageLink> getImageById(int id);
    List<ServiceDto> getServiceById(int id);
    HousesEntity getHouseByRoomId(int roomId);
}
