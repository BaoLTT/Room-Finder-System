package com.roomfindingsystem.reponsitory.service.impl.resource;

import com.roomfindingsystem.entity.DistrictEntity;
import com.roomfindingsystem.entity.ProvinceEntity;
import com.roomfindingsystem.entity.WardEntity;
import com.roomfindingsystem.service.DistrictService;
import com.roomfindingsystem.service.ProvinceService;
import com.roomfindingsystem.service.WardService;
import com.roomfindingsystem.vo.response.DistrictListResponse;
import com.roomfindingsystem.vo.response.ProvinceListResponse;
import com.roomfindingsystem.vo.response.WardListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class AddressResource {
    private final ProvinceService provinceService;
    private final DistrictService districtService;
    private final WardService wardService;

    public AddressResource(ProvinceService provinceService, DistrictService districtService, WardService wardService) {
        this.provinceService = provinceService;
        this.districtService = districtService;
        this.wardService = wardService;
    }
    @GetMapping("/province")
    public ResponseEntity<ProvinceListResponse> getProvince() {
        List<ProvinceEntity> provinceEntities = provinceService.getAll();
        ProvinceListResponse provinceListResponse = new ProvinceListResponse(provinceEntities);
        return ResponseEntity.ok(provinceListResponse);
    }

    @GetMapping("/p/{provinceId}")
    public ResponseEntity<DistrictListResponse> getDistrictsByProvince(@PathVariable Integer provinceId) {
        List<DistrictEntity> districtEntities = districtService.getDistrictsByProvince(provinceId);
        DistrictListResponse districtListResponse = new DistrictListResponse(districtEntities);
        return ResponseEntity.ok(districtListResponse);
    }

    @GetMapping("/d/{districtId}")
    public ResponseEntity<WardListResponse> getWardsByDistrict(@PathVariable Integer districtId) {
        List<WardEntity> wardEntities = wardService.getWardsByDistrict(districtId);
        WardListResponse wardListResponse = new WardListResponse(wardEntities);
        return ResponseEntity.ok(wardListResponse);
    }
}
