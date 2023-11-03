

package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class HouseDto {
    private String houseName;
    private String description;
    private LocalDate createdDate;
    private String lastName;
    private String firstName;
    private String phone;
    private String typeName;


}
