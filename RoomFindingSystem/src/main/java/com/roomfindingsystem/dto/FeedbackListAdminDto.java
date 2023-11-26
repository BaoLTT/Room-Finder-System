package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackListAdminDto {
    private int feedbackId;
    private String content;
    private LocalDate createdDate;
    private LocalDate lastModifyDate;
    private String title;
    private String houseName;
    private String userLastName;
    private String email;

}
