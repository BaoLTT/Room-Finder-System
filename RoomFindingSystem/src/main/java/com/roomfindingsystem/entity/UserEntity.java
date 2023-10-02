package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "RoomFindingSystem", catalog = "")
public class UserEntity {
    @Id
    @Basic
    @Column(name = "UserID")
    private int userId;
    @Basic
    @Column(name = "FirstName")
    private String firstName;
    @Basic
    @Column(name = "dob")
    private Timestamp dob;
    @Basic
    @Column(name = "Gender")
    private Byte gender;
    @Basic
    @Column(name = "Phone")
    private String phone;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "ImageLink")
    private String imageLink;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "FacebookID")
    private String facebookId;
    @Basic
    @Column(name = "GmailID")
    private String gmailId;
    @Basic
    @Column(name = "RoleID")
    private Integer roleId;
    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Basic
    @Column(name = "LastModifiedDate")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "UserStatusID")
    private Integer userStatusId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGmailId() {
        return gmailId;
    }

    public void setGmailId(String gmailId) {
        this.gmailId = gmailId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public Integer getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(Integer userStatusId) {
        this.userStatusId = userStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId && Objects.equals(firstName, that.firstName) && Objects.equals(dob, that.dob) && Objects.equals(gender, that.gender) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(imageLink, that.imageLink) && Objects.equals(password, that.password) && Objects.equals(facebookId, that.facebookId) && Objects.equals(gmailId, that.gmailId) && Objects.equals(roleId, that.roleId) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(userStatusId, that.userStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, dob, gender, phone, email, imageLink, password, facebookId, gmailId, roleId, createdDate, lastModifiedDate, userStatusId);
    }
}
