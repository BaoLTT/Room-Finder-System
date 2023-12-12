package com.roomfindingsystem.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseImageDto {
    private Integer imageId;
    private String imageLink;
    private Integer roomId;
}
