
package com.roomfindingsystem.dto;

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

    List<HouseImageDto> listImage;

    List<HouseServiceDto> service;

    LocalDate last_modified_date;

    Integer count_room;

    Double star;
}


