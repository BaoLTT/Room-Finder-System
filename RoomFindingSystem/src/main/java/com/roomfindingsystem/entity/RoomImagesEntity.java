package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "RoomImages", schema = "RoomFindingSystem", catalog = "")
public class RoomImagesEntity {
    @Id
    @Basic
    @Column(name = "ImageId")
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
    @Column(name = "Room_RoomID")
    private int roomRoomId;
    @Basic
    @Column(name = "Room_Slider_SliderID")
    private int roomSliderSliderId;
    @Basic
    @Column(name = "Room_Slider_User_UserID")
    private int roomSliderUserUserId;
    @Basic
    @Column(name = "Room_RoomType_RoomTYpeID")
    private int roomRoomTypeRoomTYpeId;
    @Basic
    @Column(name = "Room_Service_Room_RoomID")
    private int roomServiceRoomRoomId;
    @Basic
    @Column(name = "Room_Houses_HouseID")
    private int roomHousesHouseId;

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

    public int getRoomRoomTypeRoomTYpeId() {
        return roomRoomTypeRoomTYpeId;
    }

    public void setRoomRoomTypeRoomTYpeId(int roomRoomTypeRoomTYpeId) {
        this.roomRoomTypeRoomTYpeId = roomRoomTypeRoomTYpeId;
    }

    public int getRoomServiceRoomRoomId() {
        return roomServiceRoomRoomId;
    }

    public void setRoomServiceRoomRoomId(int roomServiceRoomRoomId) {
        this.roomServiceRoomRoomId = roomServiceRoomRoomId;
    }

    public int getRoomHousesHouseId() {
        return roomHousesHouseId;
    }

    public void setRoomHousesHouseId(int roomHousesHouseId) {
        this.roomHousesHouseId = roomHousesHouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomImagesEntity that = (RoomImagesEntity) o;
        return imageId == that.imageId && roomRoomId == that.roomRoomId && roomSliderSliderId == that.roomSliderSliderId && roomSliderUserUserId == that.roomSliderUserUserId && roomRoomTypeRoomTYpeId == that.roomRoomTypeRoomTYpeId && roomServiceRoomRoomId == that.roomServiceRoomRoomId && roomHousesHouseId == that.roomHousesHouseId && Objects.equals(imageLink, that.imageLink) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageLink, createdDate, lastModifiedDate, roomRoomId, roomSliderSliderId, roomSliderUserUserId, roomRoomTypeRoomTYpeId, roomServiceRoomRoomId, roomHousesHouseId);
    }
}
