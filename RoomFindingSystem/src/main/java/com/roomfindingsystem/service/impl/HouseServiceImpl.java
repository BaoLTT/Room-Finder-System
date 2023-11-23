package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.*;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.repository.HouseRepository;

import com.roomfindingsystem.service.HouseService;

import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service("houseService")
public class HouseServiceImpl implements HouseService {
    private HouseRepository houseRepository;


    public HouseServiceImpl(HouseRepository houseRepository) {
        super();
        this.houseRepository = houseRepository;
    }

    @Override
    public int countHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service,int countService) {
        return houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService);
    }

    public List<HouseTypeVo> findHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service,int countService, int pageIndex, int pageSize) {
        List<Tuple> tuples = houseRepository.findHouse(min1, max1, min2, max2, houseName, type, service, countService, pageIndex, pageSize);
        List<HouseTypeVo> houseTypeVos = new ArrayList<>();
        List<String> imageLinks;
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
            Long count = (tuple.get("count_room", Long.class));
            houseTypeVo.setCount_room(count.intValue());
            Long like = (tuple.get("like_house", Long.class));
            if (like == null) {
                houseTypeVo.setLike(0);
            } else {
                houseTypeVo.setLike(like.intValue());
            }

            String imageLink = (tuple.get("Image_Link", String.class));
            if (imageLink == null) {
                houseTypeVo.setListImage(null);
            } else {
                imageLinks = Arrays.asList(imageLink.split(","));
                houseTypeVo.setListImage(imageLinks);
            }

            String service1 = (tuple.get("Service_Name",String.class));
            if(service1.isEmpty())
            {houseTypeVo.setService(null);}
            else {services = Arrays.asList(service1.split(","));
                houseTypeVo.setService(services);}
            houseTypeVo.setProvince(tuple.get("province_name", String.class));
            houseTypeVo.setDistrict(tuple.get("district_name", String.class));
            houseTypeVo.setWard(tuple.get("ward_name", String.class));
            Long price = tuple.get("minPrice", Long.class);
            houseTypeVo.setPrice(price.intValue());


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
        return houseRepository.getByHouseImageid(id);
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

    public int countHousesInAdmin() {
        return houseRepository.countHouses();
    }

    @Override
    public void updateStar(double star, int houseId) {
        houseRepository.updateStarHouse(star, houseId);
    }

    @Override
    public HousesEntity getHouseById(int id) {
        return houseRepository.getHousesEntitiesByHouseId(id);
    }


    @Override


    public List<HouseHomeDto> viewHouseInHome() {
        List<Tuple> tuples = houseRepository.viewHouseInHome();

        List<HouseHomeDto> houseHomeDtos = new ArrayList<>();
        List<String> imageLinks;

        for (Tuple tuple : tuples) {


            HouseHomeDto houseHomeDto = new HouseHomeDto();
            houseHomeDto.setHouseID(tuple.get("HouseID", Integer.class));
            houseHomeDto.setHouseName(tuple.get("House_Name", String.class));
            houseHomeDto.setTypeHouse(tuple.get("Type_Name", String.class));
            String addressDetail = tuple.get("Address_Details", String.class);
            if (addressDetail == null) {
                houseHomeDto.setAddressDetail("");
            } else houseHomeDto.setAddressDetail(addressDetail);

            String imageLink = (tuple.get("Image_Link", String.class));
            if (imageLink == null) {
                houseHomeDto.setListImage(null);
            } else {
                imageLinks = Arrays.asList(imageLink.split(","));
                houseHomeDto.setListImage(imageLinks);
            }
            houseHomeDto.setProvince(tuple.get("province_name", String.class));
            houseHomeDto.setDistrict(tuple.get("district_name", String.class));
            houseHomeDto.setWard(tuple.get("ward_name", String.class));
            java.sql.Date sqlDate = (java.sql.Date) tuple.get("last_modified_date", Date.class);
            if (sqlDate == null) {
                houseHomeDto.setLast_modified_date(null);
            } else {
                LocalDate localDate = sqlDate.toLocalDate();
                houseHomeDto.setLast_modified_date(localDate);
            }

            houseHomeDto.setCountRooms(tuple.get("count_Rooms", Long.class));
            if (tuple.get("count_Empty_Rooms", Long.class) == null) {
                houseHomeDto.setCountEmptyRooms(0L);
            } else houseHomeDto.setCountEmptyRooms(tuple.get("count_Empty_Rooms", Long.class));

            houseHomeDtos.add(houseHomeDto);
        }


        return houseHomeDtos;
    }


}
