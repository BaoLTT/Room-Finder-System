package com.roomfindingsystem.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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

        LocalDate last_modified_date;
    }


