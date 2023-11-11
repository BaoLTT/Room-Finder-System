package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "slider", schema = "room_finding_system", catalog = "")
public class SliderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sliderid")
    private int sliderid;
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic
    @Column(name = "created_date")
    private Date createdDate;
    @Basic
    @Column(name = "img_link")
    private String imgLink;
    @Basic
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    @Basic
    @Column(name = "roomid")
    private Integer roomid;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "status")
    private Integer status;

    public int getSliderid() {
        return sliderid;
    }

    public void setSliderid(int sliderid) {
        this.sliderid = sliderid;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SliderEntity that = (SliderEntity) o;
        return sliderid == that.sliderid && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(imgLink, that.imgLink) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(roomid, that.roomid) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sliderid, createdBy, createdDate, imgLink, lastModifiedDate, roomid, title, content, status);
    }
}
