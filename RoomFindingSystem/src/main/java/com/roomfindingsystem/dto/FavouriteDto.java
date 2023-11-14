package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavouriteDto {
    private LocalDate createdDate;
    private int houseId;
    private int userId;
    private Boolean status;
}
