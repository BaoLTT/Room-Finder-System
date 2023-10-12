package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "blog", schema = "room_finding_system", catalog = "")
public class BlogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "BlogID")
    private int blogId;

    @Basic
    @Column(name = "Blog_Title")
    private String blogTitle;

    @Basic
    @Column(name = "Blog_Image")
    private String blogImage;

    @Basic
    @Column(name = "Blog_Content")
    private String blogContent;

    @Basic
    @Column(name = "Created_Date")
    private Timestamp createdDate;

    @Basic
    @Column(name = "Last_Modified_Date")
    private Timestamp lastModifiedDate;

    @Basic
    @Column(name = "UserID")
    private int userId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogEntity that = (BlogEntity) o;
        return blogId == that.blogId && userId == that.userId && Objects.equals(blogTitle, that.blogTitle) && Objects.equals(blogImage, that.blogImage) && Objects.equals(blogContent, that.blogContent) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogId, blogTitle, blogImage, blogContent, createdDate, lastModifiedDate, userId);
    }
}
