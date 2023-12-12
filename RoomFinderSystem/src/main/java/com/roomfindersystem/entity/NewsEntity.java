package com.roomfindersystem.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "news", schema = "room_finding_system", catalog = "")
public class NewsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "newsid")
    private int newsid;
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Basic
    @Column(name = "img_link")
    private String imgLink;
    @Basic
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;
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
    private String status;

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return newsid == that.newsid && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(imgLink, that.imgLink) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(roomid, that.roomid) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsid, createdBy, createdDate, imgLink, lastModifiedDate, roomid, title, content, status);
    }
}
