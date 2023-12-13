package com.roomfindingsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavouriteDto {
    private int houseId;
    private String housesName;
    private String typeName;
    private String addressDetail;
    private String province;
    private String district;
    private String ward;
    private Integer status;
}
