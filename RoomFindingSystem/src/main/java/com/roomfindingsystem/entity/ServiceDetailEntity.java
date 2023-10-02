package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ServiceDetail", schema = "RoomFindingSystem", catalog = "")
public class ServiceDetailEntity {
    @Id
    @Basic
    @Column(name = "ServiceId")
    private int serviceId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "CreateDate")
    private Timestamp createDate;
    @Basic
    @Column(name = "Service_Room_RoomID")
    private int serviceRoomRoomId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getServiceRoomRoomId() {
        return serviceRoomRoomId;
    }

    public void setServiceRoomRoomId(int serviceRoomRoomId) {
        this.serviceRoomRoomId = serviceRoomRoomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDetailEntity that = (ServiceDetailEntity) o;
        return serviceId == that.serviceId && serviceRoomRoomId == that.serviceRoomRoomId && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, name, description, createDate, serviceRoomRoomId);
    }
}
