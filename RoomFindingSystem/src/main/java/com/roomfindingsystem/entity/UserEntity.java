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
    private LocalDate dob;
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
    @Column(name = "GmailID", nullable = true, length = 255)
    private String gmailId;
    @Basic
    @Column(name = "RoleID")
    private String roleId;

    @Basic
    @Column(name = "Created_Date", nullable = true)
    private LocalDate createdDate;
    @Basic
    @Column(name = "Last_Modified_Date", nullable = true)
    private LocalDate lastModifiedDate;
    @Basic
    @Column(name = "User_StatusID", nullable = true)
    private Integer userStatusId;


    @Basic
    @Column(name = "Last_Name", nullable = true, length = 255)
    private String lastName;
    @Basic
    @Column(name = "AddressID", nullable = false)
    private int addressId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId && addressId == that.addressId && Objects.equals(firstName, that.firstName) && Objects.equals(dob, that.dob) && Objects.equals(gender, that.gender) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(imageLink, that.imageLink) && Objects.equals(password, that.password) && Objects.equals(gmailId, that.gmailId) && Objects.equals(roleId, that.roleId) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(userStatusId, that.userStatusId) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, dob, gender, phone, email, imageLink, password, gmailId, roleId, createdDate, lastModifiedDate, userStatusId,  lastName, addressId);
    }
    
}
