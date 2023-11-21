package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "houses", schema = "room_finding_system", catalog = "")
public class HousesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "HouseID")
    private int houseId;


    @Basic
    @Column(name = "House_Name")
    private String houseName;


    @Basic
    @Column(name = "Description")
    private String description;

    @Basic
    @Column(name = "Created_Date")
    private LocalDate createdDate;

    @Basic
    @Column(name = "Created_By")
    private Integer createdBy;


    @Basic
    @Column(name = "Last_Modified_Date")
    private LocalDate lastModifiedDate;


    @Basic
    @Column(name = "Last_Modified_By")
    private Integer lastModifiedBy;


    @Basic
    @Column(name = "AddressID")
    private int addressId;


    @Basic
    @Column(name = "Type_HouseID")
    private int typeHouseId;


    @Basic
    @Column(name = "UserID")
    private int userId;

    @Basic

    @Column(name = "star")
    private double star;



    @Column(name = "status")
    private int status;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousesEntity that = (HousesEntity) o;
        return houseId == that.houseId && addressId == that.addressId && typeHouseId == that.typeHouseId && userId == that.userId && Objects.equals(houseName, that.houseName) && Objects.equals(description, that.description) && Objects.equals(createdDate, that.createdDate) && Objects.equals(createdBy, that.createdBy) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    public HousesEntity(String houseName, String description, LocalDate createdDate, Integer createdBy, LocalDate lastModifiedDate, Integer lastModifiedBy, int addressId, int typeHouseId, int userId, int status) {
        this.houseName = houseName;
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
        this.addressId = addressId;
        this.typeHouseId = typeHouseId;
        this.userId = userId;
        this.status = status;
    }
    public HousesEntity(String houseName, String description, LocalDate lastModifiedDate, Integer lastModifiedBy, int addressId, int typeHouseId) {
        this.houseName = houseName;
        this.description = description;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
        this.addressId = addressId;
        this.typeHouseId = typeHouseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId, houseName, description, createdDate, createdBy, lastModifiedDate, lastModifiedBy, addressId, typeHouseId, userId);
    }
}
