package com.roomfindingsystem.vo;

import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDto {
    int roomId;
    String roomName;
    String houseName;
    Integer price;
    String roomType;
    List<String> roomImages;



}
