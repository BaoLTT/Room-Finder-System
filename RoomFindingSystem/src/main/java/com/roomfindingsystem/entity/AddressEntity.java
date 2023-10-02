package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Address", schema = "RoomFindingSystem", catalog = "")
public class AddressEntity {
    @Id
    @Basic
    @Column(name = "AddressID")
    private int addressId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Prefix")
    private String prefix;
    @Basic
    @Column(name = "AddressDetails")
    private String addressDetails;
    @Basic
    @Column(name = "User_UserID")
    private int userUserId;
    @Basic
    @Column(name = "Province_ProvinceID")
    private int provinceProvinceId;
    @Basic
    @Column(name = "District_DistrictID")
    private int districtDistrictId;
    @Basic
    @Column(name = "District_Province_ProvinceID")
    private int districtProvinceProvinceId;
    @Basic
    @Column(name = "Ward_WardID")
    private int wardWardId;
    @Basic
    @Column(name = "Villages_VillagesID")
    private int villagesVillagesId;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    public int getProvinceProvinceId() {
        return provinceProvinceId;
    }

    public void setProvinceProvinceId(int provinceProvinceId) {
        this.provinceProvinceId = provinceProvinceId;
    }

    public int getDistrictDistrictId() {
        return districtDistrictId;
    }

    public void setDistrictDistrictId(int districtDistrictId) {
        this.districtDistrictId = districtDistrictId;
    }

    public int getDistrictProvinceProvinceId() {
        return districtProvinceProvinceId;
    }

    public void setDistrictProvinceProvinceId(int districtProvinceProvinceId) {
        this.districtProvinceProvinceId = districtProvinceProvinceId;
    }

    public int getWardWardId() {
        return wardWardId;
    }

    public void setWardWardId(int wardWardId) {
        this.wardWardId = wardWardId;
    }

    public int getVillagesVillagesId() {
        return villagesVillagesId;
    }

    public void setVillagesVillagesId(int villagesVillagesId) {
        this.villagesVillagesId = villagesVillagesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return addressId == that.addressId && userUserId == that.userUserId && provinceProvinceId == that.provinceProvinceId && districtDistrictId == that.districtDistrictId && districtProvinceProvinceId == that.districtProvinceProvinceId && wardWardId == that.wardWardId && villagesVillagesId == that.villagesVillagesId && Objects.equals(name, that.name) && Objects.equals(prefix, that.prefix) && Objects.equals(addressDetails, that.addressDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, name, prefix, addressDetails, userUserId, provinceProvinceId, districtDistrictId, districtProvinceProvinceId, wardWardId, villagesVillagesId);
    }
}
