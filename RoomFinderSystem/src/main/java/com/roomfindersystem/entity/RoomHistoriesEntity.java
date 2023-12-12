package com.roomfindersystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "room_histories", schema = "room_finding_system", catalog = "")
public class RoomHistoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "HistoryID")
    private int historyId;

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    @Basic
    @Column(name = "Customer_Name")
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "Time_Begin")
    private LocalDate timeBegin;

    public LocalDate getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalDate timeBegin) {
        this.timeBegin = timeBegin;
    }

    @Basic
    @Column(name = "Time_End")
    private LocalDate timeEnd;

    public LocalDate getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDate timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Basic
    @Column(name = "Created_Date")
    private LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "Last_Modified_Date")
    private LocalDate lastModifiedDate;

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Basic
    @Column(name = "RoomID")
    private int roomId;

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
        RoomHistoriesEntity that = (RoomHistoriesEntity) o;
        return historyId == that.historyId && roomId == that.roomId && Objects.equals(customerName, that.customerName) && Objects.equals(timeBegin, that.timeBegin) && Objects.equals(timeEnd, that.timeEnd) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, customerName, timeBegin, timeEnd, createdDate, lastModifiedDate, roomId);
    }
}
