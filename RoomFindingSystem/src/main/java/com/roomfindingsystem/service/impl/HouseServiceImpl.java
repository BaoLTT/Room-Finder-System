package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.HousesEntity;

import com.roomfindingsystem.entity.TypeHouseEntity;
import com.roomfindingsystem.reponsitory.HouseRepository;
import com.roomfindingsystem.reponsitory.HouseTypeRepository;

import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.HouseTypeVo;
import com.roomfindingsystem.vo.ServiceDto;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service("houseService")
public class HouseServiceImpl implements HouseService {
    private HouseRepository houseRepository;




    public HouseServiceImpl(HouseRepository houseRepository){
        super();
        this.houseRepository = houseRepository;


    }

    @Override
    public int countHouse(int min, int max, String houseName, List<Integer> type, List<Integer> service) {
        return houseRepository.countHouse(min,max,houseName,type,service);
    }

    @Override
    public List<HouseTypeVo> findHouse(int min, int max, String houseName, List<Integer> type, List<Integer> service, int pageIndex, int pageSize) {
            List<Tuple> tuples = houseRepository.findHouse(min, max , houseName,type,service,pageIndex, pageSize);
            List<HouseTypeVo> houseTypeVos = new ArrayList<>();
            List<String> imageLinks ;
            List<String> services;

            for (Tuple tuple : tuples) {
                HouseTypeVo houseTypeVo = new HouseTypeVo();
                houseTypeVo.setHouseID(tuple.get("HouseID", Integer.class));
                houseTypeVo.setHouseName(tuple.get("House_Name", String.class));
                houseTypeVo.setTypeHouse(tuple.get("Type_Name", String.class));
                houseTypeVo.setAddressDetail(tuple.get("Address_Details", String.class));
                java.sql.Date sqlDate = (java.sql.Date) tuple.get("last_modified_date", Date.class);
                LocalDate localDate = sqlDate.toLocalDate();
                houseTypeVo.setLast_modified_date(localDate);
                Long count = (tuple.get("count_room",Long.class));
                houseTypeVo.setCount_room(count.intValue());
                Long like = (tuple.get("like_house",Long.class));
                if(like == null){
                    houseTypeVo.setLike(0);
                }else{
                    houseTypeVo.setLike(like.intValue());
                }
                String imageLink = (tuple.get("Image_Link", String.class));
                if(imageLink == null)
                {houseTypeVo.setListImage(null);}
                else {imageLinks = Arrays.asList(imageLink.split(","));
                    houseTypeVo.setListImage(imageLinks);}

                String service1 = (tuple.get("Service_Name",String.class));
                if(service1.isEmpty())
                {houseTypeVo.setService(null);}
                else {services = Arrays.asList(service1.split(","));
                    houseTypeVo.setService(services);}

                houseTypeVo.setProvince(tuple.get("province_name", String.class));
                houseTypeVo.setDistrict(tuple.get("district_name", String.class));
                houseTypeVo.setWard(tuple.get("ward_name", String.class));
                houseTypeVo.setPrice(tuple.get("minPrice", Integer.class));


                houseTypeVos.add(houseTypeVo);
            }
            return houseTypeVos;
    }


    @Override
    public Optional<HousesEntity> findHouseById(Integer id) {
        return houseRepository.findById(id);
    }

    @Override
    public List<HouseDto> getHouseDetail(int id) {
        return houseRepository.findAllDetail(id);
    }

    @Override
    public List<HouseImageLink> getImageById(int id) {
        return  houseRepository.getByHouseImageid(id);
    }

    @Override
    public List<ServiceDto> getServiceById(int id) {
        return houseRepository.getServiceById(id);
    }

    @Override
    public HousesEntity getHouseByRoomId(int roomId) {
        return houseRepository.findHouseByRoomId(roomId);
    }


    @Override
    public List<HouseTypeVo> viewHouseInHome() {
        List<Tuple> tuples = houseRepository.viewHouseInHome();
        List<HouseTypeVo> houseTypeVos = new ArrayList<>();
        List<String> imageLinks ;

        for (Tuple tuple : tuples) {
            HouseTypeVo houseTypeVo = new HouseTypeVo();
            houseTypeVo.setHouseID(tuple.get("HouseID", Integer.class));
            houseTypeVo.setHouseName(tuple.get("House_Name", String.class));
            houseTypeVo.setTypeHouse(tuple.get("Type_Name", String.class));
            houseTypeVo.setAddressDetail(tuple.get("Address_Details", String.class));
            String imageLink = (tuple.get("Image_Link", String.class));
            if(imageLink == null)
            {houseTypeVo.setListImage(null);}
            else {imageLinks = Arrays.asList(imageLink.split(","));
                houseTypeVo.setListImage(imageLinks);}
            houseTypeVo.setProvince(tuple.get("province_name", String.class));
            houseTypeVo.setDistrict(tuple.get("district_name", String.class));
            houseTypeVo.setWard(tuple.get("ward_name", String.class));
            houseTypeVo.setPrice(tuple.get("minPrice", Integer.class));


            houseTypeVos.add(houseTypeVo);
        }
        return houseTypeVos;
    }




}
