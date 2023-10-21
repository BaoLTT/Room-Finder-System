package com.roomfindingsystem.vo;
import com.roomfindingsystem.entity.UserEntity;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackDto {
    private int feedbackId;
    private String title;
    private String content;
    private LocalDate createdDate;
    private String userFistName;
    private String userLastName;
    private String userImage;


}
