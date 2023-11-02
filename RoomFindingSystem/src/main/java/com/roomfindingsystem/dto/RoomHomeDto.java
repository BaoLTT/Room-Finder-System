package com.roomfindingsystem.dto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomHomeDto {
    private int roomId;

    private String roomName;

    private String houseName;

    private String roomType;

    Integer price;

//    private List<String> roomImageLink;
    private String roomImageLink;

    String ward;

    String district;

    String province;

    String addressDetail;

    Double area;

}
