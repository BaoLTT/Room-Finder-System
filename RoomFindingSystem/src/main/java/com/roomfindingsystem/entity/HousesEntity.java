package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "houses", schema = "room_finding_system", catalog = "")
public class HousesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "houseid")
    private int houseId;
    @Basic
    @Column(name = "house_name")
    private String houseName;
    @Basic
    @Column(name = "type_houseid")
    private Integer typeHouseId;
    @Basic
    @Column(name = "addressid")
    private Integer addressId;
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;
    @Basic
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "userid")
    private Integer userId;
    @Basic
    @Column(name = "star")
    private Double star;
    @Basic
    @Column(name = "latitude")
    private Double latitude;
    @Basic
    @Column(name = "longitude")
    private Double longitude;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseid) {
        this.houseId = houseid;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getTypeHouseId() {
        return typeHouseId;
    }

    public void setTypeHouseId(Integer typeHouseid) {
        this.typeHouseId = typeHouseid;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressid) {
        this.addressId = addressid;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userid) {
        this.userId = userid;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousesEntity that = (HousesEntity) o;
        return houseId == that.houseId && Objects.equals(houseName, that.houseName) && Objects.equals(typeHouseId, that.typeHouseId) && Objects.equals(addressId, that.addressId) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(description, that.description) && Objects.equals(lastModifiedBy, that.lastModifiedBy) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(status, that.status) && Objects.equals(userId, that.userId) && Objects.equals(star, that.star) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId, houseName, typeHouseId, addressId, createdBy, createdDate, description, lastModifiedBy, lastModifiedDate, status, userId, star, latitude, longitude);
    }
}
