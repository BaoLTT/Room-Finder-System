

package com.roomfindersystem.dto.response;

import com.roomfindersystem.entity.DistrictEntity;
import lombok.Data;

import java.util.List;

@Data
public class DistrictListResponse {
    private List<DistrictEntity> districts;

    public DistrictListResponse(List<DistrictEntity> districts) {
        this.districts = districts;
    }
}
