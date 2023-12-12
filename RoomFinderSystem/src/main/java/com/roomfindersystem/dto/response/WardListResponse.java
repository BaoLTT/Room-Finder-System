package com.roomfindersystem.dto.response;

import com.roomfindersystem.entity.WardEntity;
import lombok.Data;

import java.util.List;

@Data
public class WardListResponse {
    private List<WardEntity> wards;

    public WardListResponse(List<WardEntity> wards) {
        this.wards = wards;
    }
}
