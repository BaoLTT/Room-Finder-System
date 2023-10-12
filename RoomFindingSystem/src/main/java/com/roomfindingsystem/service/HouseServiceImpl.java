package com.roomfindingsystem.service;



import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.reponsitory.HouseRepository;
import com.roomfindingsystem.reponsitory.HouseTypeRepository;
import com.roomfindingsystem.reponsitory.ServiceHouseRepository;
import com.roomfindingsystem.vo.HouseHomeVo;
import jakarta.persistence.EntityManager;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("houseService")
public class HouseServiceImpl implements HouseService{

    private HouseRepository houseRepository;

    private HouseTypeRepository houseTypeRepository;

    private ServiceHouseRepository serviceHouseRepository;

    @Autowired
    private EntityManager entityManager;

    public HouseServiceImpl(HouseRepository houseRepository, HouseTypeRepository houseTypeRepository,
                            ServiceHouseRepository serviceHouseRepository){
        super();
        this.houseRepository = houseRepository;
        this.houseTypeRepository = houseTypeRepository;
        this.serviceHouseRepository = serviceHouseRepository;
    }
    @Override
    public List<HousesEntity> viewTop4() {
        return houseRepository.viewTop4();
    }

    @Override
    public List<HouseHomeVo> viewTop4Home() {
        List<HouseHomeVo> list = new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        List<HouseHomeVo> filteredListB = new ArrayList<>();

        for(HouseHomeVo houseHomeVo: houseRepository.viewTop4Home()){
            int houseId = houseHomeVo.getHouseId();
            if(!set.contains(houseId)){
                set.add(houseId);
                filteredListB.add(houseHomeVo);
            }
        }
        if(filteredListB.size()<8) return filteredListB;
        else for(int i=0;i<8;i++){
            list.add(filteredListB.get(i));
//
        }
        return list;

    }

    @Override
    public List<HouseTypeEntity> getHouseType() {
        return houseTypeRepository.findAll();
    }

    @Override
    public List<ServiceDetailEntity> getHouseService() {
        return serviceHouseRepository.findAll() ;
    }

    @Override
    public List<HouseHomeVo> search(String location, List<String> typeHouse, List<String> services) {
        List<HouseHomeVo> list = new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        List<HouseHomeVo> filteredListB = new ArrayList<>();

        for(HouseHomeVo houseHomeVo: houseRepository.viewTop4HomeSearch(location, typeHouse, services)){
            int houseId = houseHomeVo.getHouseId();
            if(!set.contains(houseId)){
                set.add(houseId);
                filteredListB.add(houseHomeVo);
            }
        }

//        list.addAll(filteredListB);
        if(filteredListB.size()<8) return filteredListB;
        else for(int i=0;i<8;i++){
            list.add(filteredListB.get(i));
//            list.add(houseRepository.viewTop4Home().get(i));
        }
        return list;

    }



}
