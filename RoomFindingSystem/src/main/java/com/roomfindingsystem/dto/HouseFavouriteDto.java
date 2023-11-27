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
public class HouseFavouriteDto {

    int userId;
    int houseId;
    String houseName;
    String typeHouse;
    String addressDetail;
    String ward;
    String district;
    String province;
    List<String> listImage;
    LocalDate lastModify;

}
