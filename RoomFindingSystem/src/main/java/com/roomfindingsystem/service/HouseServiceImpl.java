package com.roomfindingsystem.service;



import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.reponsitory.HouseRepository;
import com.roomfindingsystem.vo.HouseTypeVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("houseService")
public class HouseServiceImpl implements HouseService{

    private HouseRepository houseRepository;

    public HouseServiceImpl(HouseRepository houseRepository){
        super();
        this.houseRepository = houseRepository;
    }
    @Override
    public List<HousesEntity> viewTop4() {
        return houseRepository.viewTop4();
    }

    @Override
    public List<HouseTypeVo> viewTop4Home() {
        List<HouseTypeVo> list = new ArrayList<>();
        if(!houseRepository.viewTop4Home().isEmpty()){
            for(int i = 0; i<4; i++){
                list.add(houseRepository.viewTop4Home().get(i));
            }
        }
        return list;
    }
}
