package com.roomfindingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class ServiceHouseEntityPK implements Serializable {
    @Column(name = "ServiceID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;
    @Column(name = "HouseID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int houseId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceHouseEntityPK that = (ServiceHouseEntityPK) o;
        return serviceId == that.serviceId && houseId == that.houseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, houseId);
    }
}
