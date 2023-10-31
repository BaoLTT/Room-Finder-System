package com.roomfindingsystem.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RoomDto {
    private Integer roomId;
    private Integer typeId;
    private String roomName;
    private String typeName;
    private String description;
    private Integer price;
    private Double area;
    private String services;
    private String status;
    private List<ServiceDto> serviceDtos;
    private List<String> serviceNames;
}
