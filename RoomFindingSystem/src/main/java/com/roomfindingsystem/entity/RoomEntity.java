package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Room", schema = "RoomFindingSystem", catalog = "")
public class RoomEntity {
    @Id
    @Basic
    @Column(name = "RoomID")
    private int roomId;
    @Basic
    @Column(name = "RoomName")
    private String roomName;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "Area")
    private Double area;
    @Basic
    @Column(name = "Created_Date")
    private Timestamp createdDate;
    @Basic
    @Column(name = "Last_Modified_Date")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "Slider_SliderID")
    private int sliderSliderId;
    @Basic
    @Column(name = "Slider_User_UserID")
    private int sliderUserUserId;
    @Basic
    @Column(name = "RoomType_RoomTYpeID")
    private int roomTypeRoomTYpeId;
    @Basic
    @Column(name = "Service_Room_RoomID")
    private int serviceRoomRoomId;
    @Basic
    @Column(name = "Houses_HouseID")
    private int housesHouseId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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

    public int getSliderSliderId() {
        return sliderSliderId;
    }

    public void setSliderSliderId(int sliderSliderId) {
        this.sliderSliderId = sliderSliderId;
    }

    public int getSliderUserUserId() {
        return sliderUserUserId;
    }

    public void setSliderUserUserId(int sliderUserUserId) {
        this.sliderUserUserId = sliderUserUserId;
    }

    public int getRoomTypeRoomTYpeId() {
        return roomTypeRoomTYpeId;
    }

    public void setRoomTypeRoomTYpeId(int roomTypeRoomTYpeId) {
        this.roomTypeRoomTYpeId = roomTypeRoomTYpeId;
    }

    public int getServiceRoomRoomId() {
        return serviceRoomRoomId;
    }

    public void setServiceRoomRoomId(int serviceRoomRoomId) {
        this.serviceRoomRoomId = serviceRoomRoomId;
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
        RoomEntity that = (RoomEntity) o;
        return roomId == that.roomId && sliderSliderId == that.sliderSliderId && sliderUserUserId == that.sliderUserUserId && roomTypeRoomTYpeId == that.roomTypeRoomTYpeId && serviceRoomRoomId == that.serviceRoomRoomId && housesHouseId == that.housesHouseId && Objects.equals(roomName, that.roomName) && Objects.equals(description, that.description) && Objects.equals(area, that.area) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, description, area, createdDate, lastModifiedDate, sliderSliderId, sliderUserUserId, roomTypeRoomTYpeId, serviceRoomRoomId, housesHouseId);
    }
}
