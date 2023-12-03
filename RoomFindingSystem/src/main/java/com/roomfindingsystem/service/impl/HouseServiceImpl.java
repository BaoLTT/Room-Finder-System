package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.*;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.repository.HouseRepository;

import com.roomfindingsystem.service.HouseService;

import com.roomfindingsystem.service.RoomService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseRepository houseRepository;

//    @Autowired
//    private RoomService roomService;



    @Override

    public int countHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service, int countService) {
        return houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService);

    }

    public List<HouseTypeVo> findHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service, int countService, int pageIndex, int pageSize) {
        List<Tuple> tuples = houseRepository.findHouse(min1, max1, min2, max2, houseName, type, service, countService, pageIndex, pageSize);
        List<HouseTypeVo> houseTypeVos = new ArrayList<>();
        List<String> imageLinks;
        List<String> services;
        List<String> imageIds;


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
            houseTypeVo.setStar (tuple.get("star",Double.class));
            List<HouseImageDto> listHouseImage = new ArrayList<>();
            String imageLink = (tuple.get("Image_Link", String.class));
            String imageId  = (tuple.get("Image_Id",String.class));
            if(imageLink == null)
            {houseTypeVo.setListImage(null);}
            else {
                imageLinks = Arrays.asList(imageLink.split(","));
                imageIds = Arrays.asList(imageId.split(","));
                for (int i = 0; i < imageLinks.size(); i++) {
                    HouseImageDto imageDto = new HouseImageDto();
                    imageDto.setImageLink(imageLinks.get(i));
                    imageDto.setImageId(Integer.parseInt(imageIds.get(i)));
                    listHouseImage.add(imageDto);
                }
                houseTypeVo.setListImage(listHouseImage);
            }
            List<HouseServiceDto> listService = new ArrayList<>();
            String service1 = (tuple.get("Service_Name", String.class));
            if (service1.isEmpty()) {
                houseTypeVo.setService(null);
            } else {
                services = Arrays.asList(service1.split(","));
                for (int i = 0; i < services.size(); i++) {
                    HouseServiceDto houseServiceDto = new HouseServiceDto();
                    houseServiceDto.setServiceName(services.get(i));
                    listService.add(houseServiceDto);
                }
                houseTypeVo.setService(listService);
            }
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
    public List<HousesEntity> getHouseIdByUserId(int userId) {
        return houseRepository.findHouseByMemberId(userId);
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
    public void saveHouse(HousesEntity housesEntity) {
        houseRepository.save(housesEntity);
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

            if(tuple.get("count_Empty_Rooms", Long.class) != null){
                houseHomeDtos.add(houseHomeDto);
            }



        }
        List<HouseHomeDto> houseList = new ArrayList<>();
        if(houseHomeDtos.size()<8) return houseHomeDtos;
        else {
            for(int i=0;i<8;i++){
                houseList.add(houseHomeDtos.get(i));
            }
            return houseList;
        }


    }

    @Override
    public List<HouseFavouriteDto> viewHouseInHomeInFavourite(int id) {
        List<Tuple> tuples = houseRepository.viewHouseInHomeInFavourite(id);
        List<HouseFavouriteDto> HouseFavouriteDto = new ArrayList<>();
        List<String> imageLinks;

        for (Tuple tuple : tuples) {
            HouseFavouriteDto houseHomeDto = new HouseFavouriteDto();
            houseHomeDto.setUserId(tuple.get("userid", Integer.class));
            houseHomeDto.setHouseId(tuple.get("houseId", Integer.class));
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
                houseHomeDto.setLastModify(null);
            } else {
                LocalDate localDate = sqlDate.toLocalDate();
                houseHomeDto.setLastModify(localDate);
            }


            HouseFavouriteDto.add(houseHomeDto);
        }


        return HouseFavouriteDto;
    }


}
