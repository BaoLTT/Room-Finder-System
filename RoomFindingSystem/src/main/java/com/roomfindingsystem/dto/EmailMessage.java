package com.roomfindingsystem.vo;
package com.roomfindingsystem.dto;

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
