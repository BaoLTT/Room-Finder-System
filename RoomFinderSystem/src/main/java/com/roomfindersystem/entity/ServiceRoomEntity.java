package com.roomfindersystem.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "service_room", schema = "room_finding_system", catalog = "")
@IdClass(ServiceRoomEntityPK.class)
public class ServiceRoomEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServiceID")
    private int serviceId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RoomID")
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
        ServiceRoomEntity that = (ServiceRoomEntity) o;
        return serviceId == that.serviceId && roomId == that.roomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, roomId);
    }
}
