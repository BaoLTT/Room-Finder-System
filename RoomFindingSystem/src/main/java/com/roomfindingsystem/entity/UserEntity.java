package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "room_finding_system", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserID", nullable = false)
    private int userId;
    @Basic
    @Column(name = "First_Name", nullable = true, length = 255)
    private String firstName;
    @Basic
    @Column(name = "Dob", nullable = true)
    private Timestamp dob;
    @Basic
    @Column(name = "Gender", nullable = true)
    private Boolean gender;
    @Basic
    @Column(name = "Phone", nullable = true, length = 45)
    private String phone;
    @Basic
    @Column(name = "Email", nullable = true, length = 45)
    private String email;
    @Basic
    @Column(name = "Image_Link", nullable = true, length = 255)
    private String imageLink;
    @Basic
    @Column(name = "Password", nullable = true, length = 255)
    private String password;
    @Basic
    @Column(name = "FacebookID", nullable = true, length = 255)
    private String facebookId;
    @Basic
    @Column(name = "GmailID", nullable = true, length = 255)
    private String gmailId;
    @Basic
    @Column(name = "RoleID", nullable = true)
    private Integer roleId;
    @Basic
    @Column(name = "Created_Date", nullable = true)
    private Timestamp createdDate;
    @Basic
    @Column(name = "Last_Modified_Date", nullable = true)
    private Timestamp lastModifiedDate;
    @Basic
    @Column(name = "User_StatusID", nullable = true)
    private Integer userStatusId;
    @Basic
    @Column(name = "Price", nullable = true)
    private Integer price;
    @Basic
    @Column(name = "Last_Name", nullable = true, length = 255)
    private String lastName;
    @Basic
    @Column(name = "AddressID", nullable = false)
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

    public Integer getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(Integer userStatusId) {
        this.userStatusId = userStatusId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
        return userId == that.userId && addressId == that.addressId && Objects.equals(firstName, that.firstName) && Objects.equals(dob, that.dob) && Objects.equals(gender, that.gender) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(imageLink, that.imageLink) && Objects.equals(password, that.password) && Objects.equals(facebookId, that.facebookId) && Objects.equals(gmailId, that.gmailId) && Objects.equals(roleId, that.roleId) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(userStatusId, that.userStatusId) && Objects.equals(price, that.price) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, dob, gender, phone, email, imageLink, password, facebookId, gmailId, roleId, createdDate, lastModifiedDate, userStatusId, price, lastName, addressId);
    }
}
