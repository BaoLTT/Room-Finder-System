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
    @Column(name = "UserID")
    private int userId;
    @Basic
    @NotEmpty(message = "Sorry, First name cannot be blank.!")
    @NotBlank(message = "Sorry, First name cannot be blank.!")
    @NotNull(message = "Sorry, First name cannot be blank.!")
    @Column(name = "First_Name")
    private String firstName;
    @Basic
    @NotNull(message = "Please enter birth date")
    @Past(message = "Birth date should be less than current date!!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "Dob")
    private LocalDate dob;
    @Basic
    @NotNull(message = "Sorry, Gender cannot be blank.!")
    @Column(name = "Gender")
    private String gender;
    @Basic
    @Size(min = 10,message = "Sorry,phone has 10 character!")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "example: 098 765 4321")
    @Column(name = "Phone")
    private String phone;
    @Basic
    @Email(message = "Sorry, please enter a valid Email")
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Image_Link")
    private String imageLink;
    @Basic
    @NotEmpty(message = "Sorry, Password cannot be blank.!")
    @NotBlank(message = "Sorry, Password cannot be blank.!")
    @Min(value = 8, message = "Password must be 8 characters or more!")
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
    private LocalDate createdDate;
    @Basic
    @Column(name = "Last_Modified_Date")
    private LocalDate lastModifiedDate;
    @Basic
    @Column(name = "User_StatusID")
    private Integer userStatusId;
    @Basic
    @Column(name = "Price")
    private Integer price;
    @Basic
    @NotEmpty(message = "Sorry, Last name cannot be blank.!")
    @NotBlank(message = "Sorry, Last name cannot be blank.!")
    @Column(name = "Last_Name")
    private String lastName;
    @Basic
    @Column(name = "AddressID")
    private int addressId;

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
