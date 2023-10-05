package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Houses", schema = "RoomFindingSystem", catalog = "")
public class HousesEntity {
    @Id
    @Basic
    @Column(name = "HouseID")
    private int houseId;
    @Basic
    @Column(name = "HouseName")
    private String houseName;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "TypeHouseID")
    private Integer typeHouseId;
    @Basic
    @Column(name = "ServiceID")
    private Integer serviceId;

    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Basic
    @Column(name = "CreatedBy")
    private Integer createdBy;
    @Basic
    @Column(name = "LastModifiedDate")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "LastModifiedBy")
    private Integer lastModifiedBy;
    @Basic
    @Column(name = "User_UserID")
    private int userUserId;
    @Basic
    @Column(name = "Address_AddressID")
    private int addressAddressId;


    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Integer getTypeHouseId() {
        return typeHouseId;
    }

    public void setTypeHouseId(Integer typeHouseId) {
        this.typeHouseId = typeHouseId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    public int getAddressAddressId() {
        return addressAddressId;
    }

    public void setAddressAddressId(int addressAddressId) {
        this.addressAddressId = addressAddressId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousesEntity that = (HousesEntity) o;
        return houseId == that.houseId && userUserId == that.userUserId && addressAddressId == that.addressAddressId && Objects.equals(houseName, that.houseName) && Objects.equals(description, that.description)  && Objects.equals(typeHouseId, that.typeHouseId) && Objects.equals(serviceId, that.serviceId)  && Objects.equals(createdDate, that.createdDate) && Objects.equals(createdBy, that.createdBy) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId, houseName, description,  typeHouseId, serviceId,  createdDate, createdBy, lastModifiedDate, lastModifiedBy, userUserId, addressAddressId);
    }
}
