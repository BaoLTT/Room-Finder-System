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
        LocalDate last_modified_date;
    }


