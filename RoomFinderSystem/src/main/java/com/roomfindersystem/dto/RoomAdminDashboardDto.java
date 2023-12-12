package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomAdminDashboardDto {
    int roomId;
    String houseName;
    String roomName;
    String fullName;
    String status;
    LocalDate statusUpdateDate;
}
