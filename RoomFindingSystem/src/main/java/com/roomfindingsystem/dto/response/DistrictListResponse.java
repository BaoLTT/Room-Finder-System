package com.roomfindingsystem.vo.response;
package com.roomfindingsystem.dto.response;

import com.roomfindingsystem.entity.DistrictEntity;
import lombok.Data;

import java.util.List;

@Data
public class DistrictListResponse {
    private List<DistrictEntity> districts;

    public DistrictListResponse(List<DistrictEntity> districts) {
        this.districts = districts;
    }
}
