package com.roomfindersystem.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportListDto {
    private int reportId;
    private String reportDescription;
    private LocalDate createdDate;
    private String reportStatus;
    private LocalDate solvedDate;
    private String email;
    private String houseName;

}
