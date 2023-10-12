package com.roomfindingsystem.service;




import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.vo.HouseHomeVo;

import java.util.List;

public interface HouseService {
    List<HousesEntity> viewTop4();

    List<HouseHomeVo> viewTop4Home();

    List<HouseTypeEntity> getHouseType();

    List<ServiceDetailEntity> getHouseService();

    List<HouseHomeVo> search(String location, List<String> typeHouse);




}
