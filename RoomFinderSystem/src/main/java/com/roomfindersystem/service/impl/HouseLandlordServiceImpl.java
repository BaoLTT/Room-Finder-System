package com.roomfindersystem.service.impl;

import com.roomfindersystem.dto.HouseImageDto;
import com.roomfindersystem.dto.HouseLandlordVo;
import com.roomfindersystem.repository.HouseImageRepository;
import com.roomfindersystem.repository.HouseLandlordRepository;
import com.roomfindersystem.service.HouseLandlordService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class HouseLandlordServiceImpl implements HouseLandlordService {
    @Autowired
    HouseLandlordRepository houseLandlordRepository;
    @Autowired
    HouseImageRepository houseImageRepository;
    @Override
    public List<HouseLandlordVo> findHouseByUser(int userId) {
        List<Tuple> tuples = houseLandlordRepository.findHouseByUser(userId);
        return getListHouseLandlordVo(tuples);
    }

    @Override
    public List<HouseLandlordVo> getAllHouse() {
        List<Tuple> tuples = houseLandlordRepository.getAllHouse();
        return getListHouseLandlordVo(tuples);
    }

    @Override
    public void deleteImageById(int imageId) {
        houseImageRepository.deleteById(imageId);
    }

    @Override
    public HouseLandlordVo findHouseByID(int houseid) {
        Tuple tuples = houseLandlordRepository.findHouseByID(houseid);
        return getHouseLandlordVo(tuples);
    }
    public List<HouseLandlordVo> getListHouseLandlordVo(List<Tuple> tuples) {
        List<HouseLandlordVo> houseLandlord1 = new ArrayList<>();
        List<String> imageLinks ;
        List<String> services;
        List<String> imageIds;

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
            houseLandlordVo.setStar (tuple.get("star",Double.class));
            List<HouseImageDto> listHouseImage = new ArrayList<>();
            String imageLink = (tuple.get("Image_Link", String.class));
            String imageId  = (tuple.get("Image_Id",String.class));
            if(imageLink == null)
            {houseLandlordVo.setListImage(null);}
            else {
                imageLinks = Arrays.asList(imageLink.split(","));
                imageIds = Arrays.asList(imageId.split(","));
                for (int i = 0; i < imageLinks.size(); i++) {
                    HouseImageDto imageDto = new HouseImageDto();
                    imageDto.setImageLink(imageLinks.get(i));
                    imageDto.setImageId(Integer.parseInt(imageIds.get(i)));
                    listHouseImage.add(imageDto);
                }
                houseLandlordVo.setListImage(listHouseImage);
            }

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
            Double latitude =  tuple.get("latitude",Double.class);
            Double longitude = tuple.get("longitude",Double.class);

            if(latitude==null||longitude==null){
                houseLandlordVo.setLatitude(21.0130252);
                houseLandlordVo.setLatitude(105.5239285);
            }



            houseLandlord1.add(houseLandlordVo);
        }
        return houseLandlord1;
    }

    public HouseLandlordVo getHouseLandlordVo(Tuple tuple) {
        List<String> imageLinks ;
        List<String> services;
        List<String> imageIds;

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
            houseLandlordVo.setStar (tuple.get("star",Double.class));
            List<HouseImageDto> listHouseImage = new ArrayList<>();
            String imageLink = (tuple.get("Image_Link", String.class));
            String imageId  = (tuple.get("Image_Id",String.class));
            if(imageLink == null)
            {houseLandlordVo.setListImage(null);}
            else {
                imageLinks = Arrays.asList(imageLink.split(","));
                imageIds = Arrays.asList(imageId.split(","));
                for (int i = 0; i < imageLinks.size(); i++) {
                    HouseImageDto imageDto = new HouseImageDto();
                    imageDto.setImageLink(imageLinks.get(i));
                    imageDto.setImageId(Integer.parseInt(imageIds.get(i)));
                    listHouseImage.add(imageDto);
                }
                houseLandlordVo.setListImage(listHouseImage);
            }

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

            Double latitude =  tuple.get("latitude",Double.class);
            Double longitude = tuple.get("longitude",Double.class);

            if(latitude==null||longitude==null){
                houseLandlordVo.setLatitude(21.0130252);
                houseLandlordVo.setLongitude(105.5239285);
            }
            else{
                houseLandlordVo.setLatitude(latitude);
                houseLandlordVo.setLongitude(longitude);
            }



        return houseLandlordVo;
    }


}
