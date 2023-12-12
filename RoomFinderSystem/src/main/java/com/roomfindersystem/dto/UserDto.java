
package com.roomfindersystem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDto {
    private int userId;
    private String firstName;
    private String imageLink;
    private LocalDate dob;
    private String gender;
    private String phone;
    private String email;
    private String password;
    private LocalDate createdDate;
    private String lastName;
    private Integer addressID;
    private String province;
    private String district;
    private String ward;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private String addressDetails;
    private String role;
    private String status;
}
