package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "RoomHistories", schema = "RoomFindingSystem", catalog = "")
public class RoomHistoriesEntity {
    @Id
    @Basic
    @Column(name = "RoomHistoryID")
    private int roomHistoryId;
    @Basic
    @Column(name = "CustomerName")
    private String customerName;
    @Basic
    @Column(name = "RoomHistoriescol")
    private String roomHistoriescol;
    @Basic
    @Column(name = "TimeBegin")
    private Timestamp timeBegin;
    @Basic
    @Column(name = "TimeEnd")
    private Timestamp timeEnd;
    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Basic
    @Column(name = "LastModifiedDate")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "Room_RoomID")
    private int roomRoomId;
    @Basic
    @Column(name = "Room_Slider_SliderID")
    private int roomSliderSliderId;
    @Basic
    @Column(name = "Room_Slider_User_UserID")
    private int roomSliderUserUserId;

    public int getRoomHistoryId() {
        return roomHistoryId;
    }

    public void setRoomHistoryId(int roomHistoryId) {
        this.roomHistoryId = roomHistoryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRoomHistoriescol() {
        return roomHistoriescol;
    }

    public void setRoomHistoriescol(String roomHistoriescol) {
        this.roomHistoriescol = roomHistoriescol;
    }

    public Timestamp getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Timestamp timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getRoomRoomId() {
        return roomRoomId;
    }

    public void setRoomRoomId(int roomRoomId) {
        this.roomRoomId = roomRoomId;
    }

    public int getRoomSliderSliderId() {
        return roomSliderSliderId;
    }

    public void setRoomSliderSliderId(int roomSliderSliderId) {
        this.roomSliderSliderId = roomSliderSliderId;
    }

    public int getRoomSliderUserUserId() {
        return roomSliderUserUserId;
    }

    public void setRoomSliderUserUserId(int roomSliderUserUserId) {
        this.roomSliderUserUserId = roomSliderUserUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomHistoriesEntity that = (RoomHistoriesEntity) o;
        return roomHistoryId == that.roomHistoryId && roomRoomId == that.roomRoomId && roomSliderSliderId == that.roomSliderSliderId && roomSliderUserUserId == that.roomSliderUserUserId && Objects.equals(customerName, that.customerName) && Objects.equals(roomHistoriescol, that.roomHistoriescol) && Objects.equals(timeBegin, that.timeBegin) && Objects.equals(timeEnd, that.timeEnd) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomHistoryId, customerName, roomHistoriescol, timeBegin, timeEnd, createdDate, lastModifiedDate, roomRoomId, roomSliderSliderId, roomSliderUserUserId);
    }
}
