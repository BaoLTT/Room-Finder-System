package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ward", schema = "room_finding_system", catalog = "")
public class WardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "WardID")
    private int wardId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Prefix")
    private String prefix;
    @Basic
    @Column(name = "ProvinceID")
    private int provinceId;
    @Basic
    @Column(name = "DistrictID")
    private int districtId;

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

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WardEntity that = (WardEntity) o;
        return wardId == that.wardId && provinceId == that.provinceId && districtId == that.districtId && Objects.equals(name, that.name) && Objects.equals(prefix, that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wardId, name, prefix, provinceId, districtId);
    }
}
