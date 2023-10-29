package com.roomfindingsystem.vo.response;

package com.roomfindingsystem.dto.response;

import com.roomfindingsystem.entity.ProvinceEntity;
import lombok.Data;

import java.util.List;

@Data

public class ProvinceListResponse {
    private List<ProvinceEntity> provinces;

    public ProvinceListResponse(List<ProvinceEntity> provinces) {
        this.provinces = provinces;
    }
}
