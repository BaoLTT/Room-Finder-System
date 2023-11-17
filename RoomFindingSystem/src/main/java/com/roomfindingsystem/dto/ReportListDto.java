package com.roomfindingsystem.dto;

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
    private Date createdDate;
    private String reportStatus;
    private Date solvedDate;
    private String email;
}
