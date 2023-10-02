package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Service", schema = "RoomFindingSystem", catalog = "")
public class ServiceEntity {
    @Id
    @Basic
    @Column(name = "ServiceID")
    private Integer serviceId;
    @Basic
    @Column(name = "Room_RoomID")
    private int roomRoomId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public int getRoomRoomId() {
        return roomRoomId;
    }

    public void setRoomRoomId(int roomRoomId) {
        this.roomRoomId = roomRoomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceEntity that = (ServiceEntity) o;
        return roomRoomId == that.roomRoomId && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, roomRoomId);
    }
}
