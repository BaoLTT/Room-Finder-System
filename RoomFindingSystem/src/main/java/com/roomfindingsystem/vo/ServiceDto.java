package com.roomfindingsystem.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class ServiceDto {
   public Integer serviceId;
   private String serviceName;
}
