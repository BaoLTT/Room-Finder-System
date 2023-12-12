package com.roomfindingsystem.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoomImageDto {
    private Integer imageId;
    private String imageLink;
    private Integer roomId;
}
