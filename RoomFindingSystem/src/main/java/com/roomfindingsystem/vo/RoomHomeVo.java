package com.roomfindingsystem.vo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomHomeVo {
    private int roomId;

    private String roomName;

    private String houseName;

    private String roomType;

    Integer price;

//    private String roomImageLink;

    String provinceName;

    String districtName;

    String wardName;

    String addressName;

}
