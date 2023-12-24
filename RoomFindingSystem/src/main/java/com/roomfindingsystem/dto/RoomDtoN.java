package com.roomfindingsystem.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDtoN {
    int roomId;
    String roomName;
    String houseName;
    Integer price;
    String roomType;
    String roomImages;
    String floor;



}
