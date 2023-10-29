package com.roomfindingsystem.dto.response;

import com.roomfindingsystem.entity.WardEntity;
import lombok.Data;

import java.util.List;

@Data
public class WardListResponse {
    private List<WardEntity> wards;

    public WardListResponse(List<WardEntity> wards) {
        this.wards = wards;
    }
}
