package com.roomfindingsystem.service;




import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.vo.HouseHomeVo;

import java.util.List;

public interface HouseService {
    List<HousesEntity> viewTop4();

    List<HouseHomeVo> viewTop4Home();
}
