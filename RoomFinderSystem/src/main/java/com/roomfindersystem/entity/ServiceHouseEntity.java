package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "service_house", schema = "room_finding_system", catalog = "")
@IdClass(ServiceHouseEntityPK.class)
public class ServiceHouseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServiceID")
    private int serviceId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "HouseID")
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
        ServiceHouseEntity that = (ServiceHouseEntity) o;
        return serviceId == that.serviceId && houseId == that.houseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, houseId);
    }
}
