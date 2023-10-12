package com.roomfindingsystem.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "room_finding_system", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserID")
    private int userId;
    @Basic
    @Column(name = "First_Name")
    private String firstName;
    @Basic
    @Column(name = "Dob")
    private Timestamp dob;
    @Basic
    @Column(name = "Gender")
    private Boolean gender;
    @Basic
    @Column(name = "Phone")
    private String phone;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Image_Link")
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
    @Column(name = "Created_Date")
    private Timestamp createdDate;
    @Basic
    @Column(name = "Last_Modified_Date")
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "StatusID")
    private Integer statusId;
    @Basic
    @Column(name = "Last_Name")
    private String lastName;
    @Basic
    @Column(name = "AddressID")
    private int addressId;

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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId && addressId == that.addressId && Objects.equals(firstName, that.firstName) && Objects.equals(dob, that.dob) && Objects.equals(gender, that.gender) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(imageLink, that.imageLink) && Objects.equals(password, that.password) && Objects.equals(facebookId, that.facebookId) && Objects.equals(gmailId, that.gmailId) && Objects.equals(roleId, that.roleId) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(statusId, that.statusId) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, dob, gender, phone, email, imageLink, password, facebookId, gmailId, roleId, createdDate, lastModifiedDate, statusId, lastName, addressId);
    }
}
