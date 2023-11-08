

package com.roomfindingsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ServiceDto {
   private Integer serviceId;
   private String serviceName;
   private String description;

   public ServiceDto(String serviceName, String description) {
      this.serviceName = serviceName;
      this.description = description;
   }
}
