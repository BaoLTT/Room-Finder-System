package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.entity.HousesEntity;


import java.util.List;
import java.util.Optional;

public interface HouseService {
    //Th
    int countHouse(int min1, int max1, int min2, int max2,int province,int district,int ward,int status1,int status2, String houseName, List<Integer> type, List<Integer> service,int countService);

    List<HouseTypeVo> findHouse(int min1, int max1, int min2, int max2,int province,int district,int ward,int status1,int status2,String houseName,List<Integer> type, List<Integer> service,int countService, int pageIndex,int pageSize);

    List<HousesEntity> getAllHouse();
    List<HouseHomeDto> viewHouseInHome();
    List<HouseFavouriteDto> viewHouseInHomeInFavourite(int id);
    Optional<HousesEntity> findHouseById(Integer id);
    List<HouseDto> getHouseDetail(int id);
    List<HouseImageLink> getImageById(int id);
    List<ServiceDto> getServiceById(int id);
    HousesEntity getHouseByRoomId(int roomId);

    List<HousesEntity> getHouseIdByUserId(int userId);

    //admin
    int countHousesInAdmin();

    void updateStar(double star, int houseId);

    HousesEntity getHouseById(int id);

}
