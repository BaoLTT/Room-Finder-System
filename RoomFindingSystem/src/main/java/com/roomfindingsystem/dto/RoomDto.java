package com.roomfindingsystem.dto;

import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDto {
    private Integer roomId;
    private Integer typeId;
    private String roomName;
    private Integer floor;
    private String typeName;
    private Integer createdBy;
    private Integer lastModifiedBy;
    private String description;
    private Integer price;
    private Double area;
    private String services;
    private String status;
    private List<ServiceDto> serviceDtos;
    private List<String> serviceNames;
    private List<RoomImageDto> imgs;
    private Integer houseId;
    private String houseName;

    public RoomDto(Integer roomId, String roomName,Integer floor, String typeName, String description, Integer price, String houseName, Double area, String status) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.floor= floor;
        this.typeName = typeName;
        this.description = description;
        this.price = price;
        this.houseName = houseName;
        this.area = area;
        this.status = status;
    }
}
