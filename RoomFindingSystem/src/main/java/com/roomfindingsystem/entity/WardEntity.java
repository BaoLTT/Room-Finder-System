package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Ward", schema = "RoomFindingSystem", catalog = "")
public class WardEntity {
    @Id
    @Basic
    @Column(name = "WardID")
    private int wardId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Prefix")
    private String prefix;
    @Basic
    @Column(name = "District_DistrictID")
    private int districtDistrictId;
    @Basic
    @Column(name = "District_Province_ProvinceID")
    private int districtProvinceProvinceId;

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WardEntity that = (WardEntity) o;
        return wardId == that.wardId && districtDistrictId == that.districtDistrictId && districtProvinceProvinceId == that.districtProvinceProvinceId && Objects.equals(name, that.name) && Objects.equals(prefix, that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wardId, name, prefix, districtDistrictId, districtProvinceProvinceId);
    }
}
