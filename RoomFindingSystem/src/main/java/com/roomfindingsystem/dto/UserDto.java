
package com.roomfindingsystem.dto;

import lombok.*;

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
    private String dob;
    private String gender;
    private String phone;
    private String email;
    private String password;
    private String createdDate;
    private String lastName;
    private String province;
    private String district;
    private String ward;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private String addressDetails;
}
