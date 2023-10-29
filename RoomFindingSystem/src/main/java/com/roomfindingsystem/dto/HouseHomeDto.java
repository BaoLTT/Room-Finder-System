package com.roomfindingsystem.dto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseHomeDto {
    int houseID;

    String houseName;

    String typeHouse;

    String addressDetail;

    String ward;

    String district;

    String province;

    List<String> listImage;
    LocalDate last_modified_date;
    Long countRooms;
}
