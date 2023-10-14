package com.roomfindingsystem.vo;

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
    private LocalDate dob;
    private String gender;
    private String phone;
    private String email;
    private String password;
    private String createdDate;
    private String lastName;
}
