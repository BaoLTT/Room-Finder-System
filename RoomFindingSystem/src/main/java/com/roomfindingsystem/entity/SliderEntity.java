package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "slider", schema = "room_finding_system", catalog = "")
public class SliderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SliderID")
    private int sliderId;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "Img_Link")
    private String imgLink;
    @Basic
    @Column(name = "Created_Date")
    private Timestamp createdDate;
    @Basic
    @Column(name = "Last_Modified_Date")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "RoomID")
    private int roomId;
    @Basic
    @Column(name = "Last_Modified_By")
    private Integer lastModifiedBy;
    @Basic
    @Column(name = "Created_By")
    private int createdBy;

    public int getSliderId() {
        return sliderId;
    }

    public void setSliderId(int sliderId) {
        this.sliderId = sliderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SliderEntity that = (SliderEntity) o;
        return sliderId == that.sliderId && roomId == that.roomId && createdBy == that.createdBy && Objects.equals(title, that.title) && Objects.equals(imgLink, that.imgLink) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(lastModifiedBy, that.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sliderId, title, imgLink, createdDate, lastModifiedDate, roomId, lastModifiedBy, createdBy);
    }
}
