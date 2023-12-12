package com.roomfindersystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "address", schema = "room_finding_system", catalog = "")
public class AddressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "AddressID")
    private int addressId;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "Address_Details")
    private String addressDetails;

    @Basic
    @Column(name = "ProvinceID")
    private int provinceId;

    @Basic
    @Column(name = "DistrictID")
    private int districtId;

    @Basic
    @Column(name = "WardID")
    private int wardId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return addressId == that.addressId && provinceId == that.provinceId && districtId == that.districtId && wardId == that.wardId && Objects.equals(name, that.name) && Objects.equals(addressDetails, that.addressDetails);
    }

    public AddressEntity(String name, String addressDetails, int provinceId, int districtId, int wardId) {
        this.name = name;
        this.addressDetails = addressDetails;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, name, addressDetails, provinceId, districtId, wardId);
    }
}
