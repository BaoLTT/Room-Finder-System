

package com.roomfindingsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class ServiceDto {
   private Integer serviceId;
   private String serviceName;
   private String description;
}
