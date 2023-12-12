

package com.roomfindingsystem.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackDtoAdmin {
    private int feedbackId;
    private String title;
    private String content;
    private LocalDate createdDate;
    private String userFistName;
    private String userLastName;
    private String userImage;
    private int userId;
    private int star;
    private boolean status;
    private String houseName;


}
