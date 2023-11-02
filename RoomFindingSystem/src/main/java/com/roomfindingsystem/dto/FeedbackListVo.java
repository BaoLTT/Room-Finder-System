package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackListVo {
    private int feedbackId;
    private String feedbackContent;
    private  String feedbacktitle;
    private LocalDate createdDate;

}
