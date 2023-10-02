package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "District", schema = "RoomFindingSystem", catalog = "")
public class DistrictEntity {
    @Id
    @Basic
    @Column(name = "DistrictID")
    private int districtId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Prefix")
    private String prefix;
    @Basic
    @Column(name = "Province_ProvinceID")
    private int provinceProvinceId;

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
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

    public int getProvinceProvinceId() {
        return provinceProvinceId;
    }

    public void setProvinceProvinceId(int provinceProvinceId) {
        this.provinceProvinceId = provinceProvinceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictEntity that = (DistrictEntity) o;
        return districtId == that.districtId && provinceProvinceId == that.provinceProvinceId && Objects.equals(name, that.name) && Objects.equals(prefix, that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(districtId, name, prefix, provinceProvinceId);
    }
}
