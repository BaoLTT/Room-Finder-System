package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "RoomType", schema = "RoomFindingSystem", catalog = "")
public class RoomTypeEntity {
    @Id
    @Basic
    @Column(name = "RoomTYpeID")
    private int roomTYpeId;
    @Basic
    @Column(name = "RoomTypeName")
    private String roomTypeName;
    @Basic
    @Column(name = "Created_Date")
    private Timestamp createdDate;

    public int getRoomTYpeId() {
        return roomTYpeId;
    }

    public void setRoomTYpeId(int roomTYpeId) {
        this.roomTYpeId = roomTYpeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomTypeEntity that = (RoomTypeEntity) o;
        return roomTYpeId == that.roomTYpeId && Objects.equals(roomTypeName, that.roomTypeName) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTYpeId, roomTypeName, createdDate);
    }
}
