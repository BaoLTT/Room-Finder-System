package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Villages", schema = "RoomFindingSystem", catalog = "")
public class VillagesEntity {
    @Id
    @Basic
    @Column(name = "VillagesID")
    private int villagesId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Prefix")
    private String prefix;
    @Basic
    @Column(name = "Ward_WardID")
    private int wardWardId;
    @Basic
    @Column(name = "Ward_District_DistrictID")
    private int wardDistrictDistrictId;
    @Basic
    @Column(name = "Ward_District_Province_ProvinceID")
    private int wardDistrictProvinceProvinceId;

    public int getVillagesId() {
        return villagesId;
    }

    public void setVillagesId(int villagesId) {
        this.villagesId = villagesId;
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

    public int getWardWardId() {
        return wardWardId;
    }

    public void setWardWardId(int wardWardId) {
        this.wardWardId = wardWardId;
    }

    public int getWardDistrictDistrictId() {
        return wardDistrictDistrictId;
    }

    public void setWardDistrictDistrictId(int wardDistrictDistrictId) {
        this.wardDistrictDistrictId = wardDistrictDistrictId;
    }

    public int getWardDistrictProvinceProvinceId() {
        return wardDistrictProvinceProvinceId;
    }

    public void setWardDistrictProvinceProvinceId(int wardDistrictProvinceProvinceId) {
        this.wardDistrictProvinceProvinceId = wardDistrictProvinceProvinceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VillagesEntity that = (VillagesEntity) o;
        return villagesId == that.villagesId && wardWardId == that.wardWardId && wardDistrictDistrictId == that.wardDistrictDistrictId && wardDistrictProvinceProvinceId == that.wardDistrictProvinceProvinceId && Objects.equals(name, that.name) && Objects.equals(prefix, that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(villagesId, name, prefix, wardWardId, wardDistrictDistrictId, wardDistrictProvinceProvinceId);
    }
}
