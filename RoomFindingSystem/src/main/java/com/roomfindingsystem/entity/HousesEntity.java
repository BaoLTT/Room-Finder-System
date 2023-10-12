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
    @Column(name = "Service_ID")
    private Integer serviceId;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousesEntity that = (HousesEntity) o;
        return houseId == that.houseId && addressId == that.addressId && typeHouseId == that.typeHouseId && userId == that.userId && Objects.equals(houseName, that.houseName) && Objects.equals(description, that.description) && Objects.equals(serviceId, that.serviceId) && Objects.equals(createdDate, that.createdDate) && Objects.equals(createdBy, that.createdBy) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId, houseName, description, serviceId, createdDate, createdBy, lastModifiedDate, lastModifiedBy, addressId, typeHouseId, userId);
    }
}
