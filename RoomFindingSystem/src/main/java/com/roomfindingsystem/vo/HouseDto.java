package com.roomfindingsystem.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class HouseDto {
    private int  houseId;
    private String houseName;
    private String description;
    private LocalDate createdDate;
    private String lastName;
    private String firstName;
    private String phoneLandLord;
    private String serviceName;
    private int address;
    private String typeName;


}
