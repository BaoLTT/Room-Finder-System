package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "post", schema = "room_finding_system", catalog = "")
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "PostID")
    private int postId;

    @Basic
    @Column(name = "Post_Title")
    private String postTitle;

    @Basic
    @Column(name = "Post_Image")
    private String postImage;

    @Basic
    @Column(name = "Post_Content")
    private String postContent;

    @Basic
    @Column(name = "Created_Date")
    private LocalDate createdDate;

    @Basic
    @Column(name = "Last_Modified_Date")
    private LocalDate lastModifiedDate;

    @Basic
    @Column(name = "UserID")
    private int userId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return postId == that.postId && userId == that.userId && Objects.equals(postTitle, that.postTitle) && Objects.equals(postImage, that.postImage) && Objects.equals(postContent, that.postContent) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postTitle, postImage, postContent, createdDate, lastModifiedDate, userId);
    }
}
