

package com.roomfindersystem.dto.response;

import com.roomfindersystem.entity.ProvinceEntity;
import lombok.Data;

import java.util.List;

@Data

public class ProvinceListResponse {
    private List<ProvinceEntity> provinces;

    public ProvinceListResponse(List<ProvinceEntity> provinces) {
        this.provinces = provinces;
    }
}
