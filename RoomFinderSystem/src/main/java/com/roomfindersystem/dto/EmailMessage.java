

package com.roomfindersystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmailMessage {
    private String to;
    private String subject;
    private String message;

}
