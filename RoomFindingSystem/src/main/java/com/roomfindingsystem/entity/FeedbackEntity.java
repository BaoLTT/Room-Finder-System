package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Feedback", schema = "RoomFindingSystem", catalog = "")
public class FeedbackEntity {
    @Id
    @Basic
    @Column(name = "FeedbackID")
    private int feedbackId;
    @Basic
    @Column(name = "Content")
    private String content;
    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Basic
    @Column(name = "LastModifiedDate")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "Houses_HouseID")
    private int housesHouseId;
    @Basic
    @Column(name = "User_UserID")
    private int userUserId;

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackEntity that = (FeedbackEntity) o;
        return feedbackId == that.feedbackId && housesHouseId == that.housesHouseId && userUserId == that.userUserId && Objects.equals(content, that.content) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, content, createdDate, lastModifiedDate, housesHouseId, userUserId);
    }
}
