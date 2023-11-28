package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseLandlordVo {
    private int houseID;

    private String houseName;

    private String description;

    private LocalDate createdDate;

    private String created_firstName;

    private String created_lastName;

    private Integer createdBy;

    private String addressDetail;

    private int address;

    private String ward;

    private String district;

    private String province;

    private int wardID;

    private int districtID;

    private int provinceID;

    private String typeHouse;

    private int typeHouseID;

    private LocalDate lastModifiedDate;

    private String lastModifiedBy_firstName;

    private String lastModifiedBy_lastName;

    private Integer lastModifiedBy;

    private String user_firstName;

    private String user_lastName;

    private Integer userID;

    private Integer count_room;

    private Integer price;

    List<HouseImageDto> listImage;

    List<String> service;

    private Integer like;

    private int status;

    private double latitude;

    private double longitude;

    public HouseLandlordVo(int houseID, String houseName, String description, LocalDate createdDate, String created_firstName, String created_lastName, String addressDetail, int address, String ward, String district, String province, int wardID, int districtID, int provinceID, String typeHouse, int typeHouseID, LocalDate lastModifiedDate, String lastModifiedBy_firstName, String lastModifiedBy_lastName, String user_firstName, String user_lastName, int status) {
        this.houseID = houseID;
        this.houseName = houseName;
        this.description = description;
        this.createdDate = createdDate;
        this.created_firstName = created_firstName;
        this.created_lastName = created_lastName;
        this.addressDetail = addressDetail;
        this.address = address;
        this.ward = ward;
        this.district = district;
        this.province = province;
        this.wardID = wardID;
        this.districtID = districtID;
        this.provinceID = provinceID;
        this.typeHouse = typeHouse;
        this.typeHouseID = typeHouseID;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy_firstName = lastModifiedBy_firstName;
        this.lastModifiedBy_lastName = lastModifiedBy_lastName;
        this.user_firstName = user_firstName;
        this.user_lastName = user_lastName;
        this.status = status;
    }
}
