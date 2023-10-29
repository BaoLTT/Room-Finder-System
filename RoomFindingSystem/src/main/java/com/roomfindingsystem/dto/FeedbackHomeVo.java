package com.roomfindingsystem.vo;

package com.roomfindingsystem.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackHomeVo {
    String firstName;

    String lastName;

    String houseName;

    //so luot like

    String content;

    int houseId;

    String provinceName;

    String districtName;

    String wardName;

    String addressName;
}
