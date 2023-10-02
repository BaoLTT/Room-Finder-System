package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Province", schema = "RoomFindingSystem", catalog = "")
public class ProvinceEntity {
    @Id
    @Basic
    @Column(name = "ProvinceID")
    private int provinceId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Code")
    private String code;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceEntity that = (ProvinceEntity) o;
        return provinceId == that.provinceId && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provinceId, name, code);
    }
}
