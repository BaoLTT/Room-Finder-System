package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "room_histories", schema = "room_finding_system", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
public class RoomHistoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "historyid")
    private int historyid;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "roomid")
    private Integer roomid;
    @Basic
    @Column(name = "start_date")
    private LocalDate startDate;

    public int getHistoryid() {
        return historyid;
    }

    public void setHistoryid(int historyid) {
        this.historyid = historyid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public RoomHistoriesEntity(Integer roomid,Integer status,  LocalDate startDate) {
        this.status = status;
        this.roomid = roomid;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomHistoriesEntity that = (RoomHistoriesEntity) o;
        return historyid == that.historyid && Objects.equals(status, that.status) && Objects.equals(roomid, that.roomid) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyid, status, roomid, startDate);
    }
}
