package com.roomfindingsystem.dto;

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
