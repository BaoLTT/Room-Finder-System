package com.roomfindingsystem.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class HouseTypeVo {
        int houseID;

        String houseName;

        String typeHouse;

        String addressDetail;

        String ward;

        String district;

        String province;

        Integer price;

        List<String> listImage;

        List<String> service;

        LocalDate last_modified_date;

        Integer count_room;

        Integer like;
    }


