package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.repository.HouseLandlordRepository;
import com.roomfindingsystem.service.HouseLandlordService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class HouseLandlordServiceImpl implements HouseLandlordService {
    @Autowired
    HouseLandlordRepository houseLandlordRepository;
    @Override
    public List<HouseLandlordVo> findHouse(int userId) {
        List<Tuple> tuples = houseLandlordRepository.findHouse(userId);
        return getHouseLandlordVo(tuples);
    }

    @Override
    public HouseLandlordVo findHouseByID(int houseid) {
        Tuple tuples = houseLandlordRepository.findHouseByID(houseid);
        return getHouseLandlordVo(tuples);
    }
    public List<HouseLandlordVo> getHouseLandlordVo(List<Tuple> tuples) {
        List<HouseLandlordVo> houseLandlord1 = new ArrayList<>();
        List<String> imageLinks ;
        List<String> services;

        for (Tuple tuple : tuples) {
            HouseLandlordVo houseLandlordVo = new HouseLandlordVo();
            houseLandlordVo.setHouseID(tuple.get("HouseID", Integer.class));
            houseLandlordVo.setStatus(tuple.get("status", Integer.class));
            houseLandlordVo.setHouseName(tuple.get("House_Name", String.class));
            houseLandlordVo.setTypeHouse(tuple.get("Type_Name", String.class));
            houseLandlordVo.setAddressDetail(tuple.get("Address_Details", String.class));
            houseLandlordVo.setDescription(tuple.get("description",String.class));
            java.sql.Date sqlCreated = (java.sql.Date) tuple.get("created_date", Date.class);
            LocalDate localCreated = sqlCreated.toLocalDate();
            houseLandlordVo.setCreatedDate(localCreated);
            houseLandlordVo.setCreated_firstName(tuple.get("first_name",String.class));
            houseLandlordVo.setCreated_lastName(tuple.get("last_name",String.class));
            houseLandlordVo.setAddress(tuple.get("addressid",Integer.class));
            houseLandlordVo.setTypeHouseID(tuple.get("type_houseid",Integer.class));
            houseLandlordVo.setLastModifiedBy_firstName(tuple.get("last_modified_byFirstName",String.class));
            houseLandlordVo.setLastModifiedBy_lastName(tuple.get("last_modified_byLastName",String.class));
            houseLandlordVo.setUser_firstName(tuple.get("userFirstName",String.class));
            houseLandlordVo.setUser_lastName(tuple.get("userLastName",String.class));
            java.sql.Date sqlLast = (java.sql.Date) tuple.get("last_modified_date", Date.class);
            LocalDate localLast = sqlLast.toLocalDate();
            houseLandlordVo.setLastModifiedDate(localLast);
            Long count = (tuple.get("count_Rooms",Long.class));
            if(count==null){
                houseLandlordVo.setCount_room(0);
            }else{
                houseLandlordVo.setCount_room(count.intValue());
            }

            Long like = (tuple.get("like_House",Long.class));
            if(like == null){
                houseLandlordVo.setLike(0);
            }else{
                houseLandlordVo.setLike(like.intValue());
            }

            String imageLink = (tuple.get("Image_Link", String.class));
            if(imageLink == null)
            {houseLandlordVo.setListImage(null);}
            else {imageLinks = Arrays.asList(imageLink.split(","));
                houseLandlordVo.setListImage(imageLinks);}

            String service1 = (tuple.get("Service_Name",String.class));
            if (service1 == null || service1.isEmpty()) {
                houseLandlordVo.setService(Collections.emptyList());
            } else {
                services = Arrays.asList(service1.split(","));
                houseLandlordVo.setService(services);
            }

            houseLandlordVo.setProvince(tuple.get("province_name", String.class));
            houseLandlordVo.setDistrict(tuple.get("district_name", String.class));
            houseLandlordVo.setWard(tuple.get("ward_name", String.class));
            houseLandlordVo.setProvinceID(tuple.get("provinceID",Integer.class));
            houseLandlordVo.setDistrictID(tuple.get("districtID",Integer.class));
            houseLandlordVo.setWardID(tuple.get("wardID",Integer.class));
            Long price = tuple.get("minPrice", Long.class);

            if (price == null ) {
                houseLandlordVo.setPrice(0);
            } else {
                houseLandlordVo.setPrice(price.intValue());
            }

            houseLandlord1.add(houseLandlordVo);
        }
        return houseLandlord1;
    }

    public HouseLandlordVo getHouseLandlordVo(Tuple tuple) {
        List<String> imageLinks ;
        List<String> services;

            HouseLandlordVo houseLandlordVo = new HouseLandlordVo();
            houseLandlordVo.setHouseID(tuple.get("HouseID", Integer.class));
            houseLandlordVo.setStatus(tuple.get("status", Integer.class));
            houseLandlordVo.setHouseName(tuple.get("House_Name", String.class));
            houseLandlordVo.setTypeHouse(tuple.get("Type_Name", String.class));
            houseLandlordVo.setAddressDetail(tuple.get("Address_Details", String.class));
            houseLandlordVo.setDescription(tuple.get("description",String.class));
            java.sql.Date sqlCreated = (java.sql.Date) tuple.get("created_date", Date.class);
            LocalDate localCreated = sqlCreated.toLocalDate();
            houseLandlordVo.setCreatedDate(localCreated);
            houseLandlordVo.setCreated_firstName(tuple.get("first_name",String.class));
            houseLandlordVo.setCreated_lastName(tuple.get("last_name",String.class));
            houseLandlordVo.setAddress(tuple.get("addressid",Integer.class));
            houseLandlordVo.setTypeHouseID(tuple.get("type_houseid",Integer.class));
            houseLandlordVo.setLastModifiedBy_firstName(tuple.get("last_modified_byFirstName",String.class));
            houseLandlordVo.setLastModifiedBy_lastName(tuple.get("last_modified_byLastName",String.class));
            houseLandlordVo.setUser_firstName(tuple.get("userFirstName",String.class));
            houseLandlordVo.setUser_lastName(tuple.get("userLastName",String.class));
            java.sql.Date sqlLast = (java.sql.Date) tuple.get("last_modified_date", Date.class);
            LocalDate localLast = sqlLast.toLocalDate();
            houseLandlordVo.setLastModifiedDate(localLast);
            Long count = (tuple.get("count_Rooms",Long.class));
            if(count==null){
                houseLandlordVo.setCount_room(0);
            }else{
                houseLandlordVo.setCount_room(count.intValue());
            }

            Long like = (tuple.get("like_House",Long.class));
            if(like == null){
                houseLandlordVo.setLike(0);
            }else{
                houseLandlordVo.setLike(like.intValue());
            }

            String imageLink = (tuple.get("Image_Link", String.class));
            if(imageLink == null)
            {houseLandlordVo.setListImage(null);}
            else {imageLinks = Arrays.asList(imageLink.split(","));
                houseLandlordVo.setListImage(imageLinks);}

            String service1 = (tuple.get("Service_Name",String.class));
            if (service1 == null || service1.isEmpty()) {
                houseLandlordVo.setService(Collections.emptyList());
            } else {
                services = Arrays.asList(service1.split(","));
                houseLandlordVo.setService(services);
            }

            houseLandlordVo.setProvince(tuple.get("province_name", String.class));
            houseLandlordVo.setDistrict(tuple.get("district_name", String.class));
            houseLandlordVo.setWard(tuple.get("ward_name", String.class));
            houseLandlordVo.setProvinceID(tuple.get("provinceID",Integer.class));
            houseLandlordVo.setDistrictID(tuple.get("districtID",Integer.class));
            houseLandlordVo.setWardID(tuple.get("wardID",Integer.class));
            Long price = tuple.get("minPrice", Long.class);

            if (price == null ) {
                houseLandlordVo.setPrice(0);
            } else {
                houseLandlordVo.setPrice(price.intValue());
            }



        return houseLandlordVo;
    }


}
