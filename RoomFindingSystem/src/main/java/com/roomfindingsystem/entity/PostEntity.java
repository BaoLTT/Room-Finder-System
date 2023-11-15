package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "room_finding_system", catalog = "")
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "postid")
    private int postid;
    @Basic
    @Column(name = "post_content")
    private String postContent;
    @Basic
    @Column(name = "post_image")
    private String postImage;
    @Basic
    @Column(name = "post_title")
    private String postTitle;
    @Basic
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Basic
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;
    @Basic
    @Column(name = "userid")
    private Integer userid;
    @Basic
    @Column(name = "status")
    private Integer status;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
        PostEntity that = (PostEntity) o;
        return postid == that.postid && Objects.equals(postContent, that.postContent) && Objects.equals(postImage, that.postImage) && Objects.equals(postTitle, that.postTitle) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(userid, that.userid) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postid, postContent, postImage, postTitle, createdDate, lastModifiedDate, userid, status);
    }
}
