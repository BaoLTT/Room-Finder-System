package com.roomfindingsystem.dto;

import com.roomfindingsystem.entity.ServiceDetailEntity;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomHouseDetailDto {
    Integer roomId;

    Integer typeId;

    String typeName;

    List<String> roomList;

//    List<String> idList;

    Integer houseId;

    String houseName;

    Integer price;

    List<String> serviceList;

//    Integer status;



}
