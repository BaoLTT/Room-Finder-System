package com.roomfindingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class ServiceRoomEntityPK implements Serializable {
    @Column(name = "ServiceID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;
    @Column(name = "RoomID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRoomEntityPK that = (ServiceRoomEntityPK) o;
        return serviceId == that.serviceId && roomId == that.roomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, roomId);
    }
}
