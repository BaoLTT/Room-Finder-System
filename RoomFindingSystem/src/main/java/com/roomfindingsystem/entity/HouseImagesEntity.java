package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "HouseImages", schema = "RoomFindingSystem", catalog = "")
public class HouseImagesEntity {
    @Id
    @Basic
    @Column(name = "ImageID")
    private int imageId;
    @Basic
    @Column(name = "ImageLink")
    private String imageLink;
    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Basic
    @Column(name = "LastModifiedDate")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "Houses_HouseID")
    private int housesHouseId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public int getHousesHouseId() {
        return housesHouseId;
    }

    public void setHousesHouseId(int housesHouseId) {
        this.housesHouseId = housesHouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseImagesEntity that = (HouseImagesEntity) o;
        return imageId == that.imageId && housesHouseId == that.housesHouseId && Objects.equals(imageLink, that.imageLink) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageLink, createdDate, lastModifiedDate, housesHouseId);
    }
}
