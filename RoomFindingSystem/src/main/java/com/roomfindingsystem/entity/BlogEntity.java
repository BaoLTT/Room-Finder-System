package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Blog", schema = "RoomFindingSystem", catalog = "")
public class BlogEntity {
    @Id
    @Basic
    @Column(name = "BlogID")
    private int blogId;
    @Basic
    @Column(name = "BlogTitle")
    private Integer blogTitle;
    @Basic
    @Column(name = "BlogImage")
    private String blogImage;
    @Basic
    @Column(name = "BlogContent")
    private String blogContent;
    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Basic
    @Column(name = "LastModifiedDate")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "User_UserID")
    private int userUserId;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public Integer getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(Integer blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
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
        BlogEntity that = (BlogEntity) o;
        return blogId == that.blogId && userUserId == that.userUserId && Objects.equals(blogTitle, that.blogTitle) && Objects.equals(blogImage, that.blogImage) && Objects.equals(blogContent, that.blogContent) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogId, blogTitle, blogImage, blogContent, createdDate, lastModifiedDate, userUserId);
    }
}
