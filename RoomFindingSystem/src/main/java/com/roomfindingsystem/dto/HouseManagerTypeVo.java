package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseManagerTypeVo {
     private int houseID;

    private String houseName;

    private String description;

    private LocalDate createdDate;

    private String created_firstName;

    private String created_lastName;

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

    private String user_firstName;

    private String user_lastName;



    public HouseManagerTypeVo(int houseID, String houseName, String addressDetail, String province, String district, String ward, String typeHouse, LocalDate lastModifiedDate, String lastModifiedBy_firstName, String lastModifiedBy_lastName, String user_firstName, String user_lastName) {
        this.houseID = houseID;
        this.houseName = houseName;
        this.addressDetail = addressDetail;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.typeHouse = typeHouse;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy_firstName = lastModifiedBy_firstName;
        this.lastModifiedBy_lastName = lastModifiedBy_lastName;
        this.user_firstName= user_firstName;
        this.user_lastName = user_lastName;
    }
}
