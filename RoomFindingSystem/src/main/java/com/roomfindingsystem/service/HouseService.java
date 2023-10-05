package com.roomfindingsystem.service;




import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.vo.HouseTypeVo;

import java.util.List;

public interface HouseService {
    List<HousesEntity> viewTop4();

    List<HouseTypeVo> viewTop4Home();
}
