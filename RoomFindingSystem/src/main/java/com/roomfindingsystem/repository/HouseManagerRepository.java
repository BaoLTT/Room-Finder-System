package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HousesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("houseManagerRepository")
public interface HouseManagerRepository extends JpaRepository<HousesEntity,Integer> {
    @Query("select new com.roomfindingsystem.dto.HouseManagerTypeVo(h.houseId, h.houseName, a.addressDetails, p.name, d.name, w.name,t.typeName, h.lastModifiedDate,u.firstName,u.lastName,u1.firstName,u1.lastName) from HousesEntity h " +
            "join TypeHouseEntity t on h.typeHouseId = t.typeId " +
            "join UserEntity u on h.lastModifiedBy = u.userId " +
            "join UserEntity u1 on h.userId = u1.userId " +
            "left join AddressEntity a on h.addressId = a.addressId " +
            "left join ProvinceEntity p on a.provinceId = p.provinceId " +
            "left join DistrictEntity d on a.districtId = d.districtId " +
            "left join WardEntity w on w.wardId = a.wardId " )
    List<HouseManagerTypeVo> findHouseManager();
    @Query("select new com.roomfindingsystem.dto.HouseManagerTypeVo(h.houseId, h.houseName, h.description, h.createdDate, u1.firstName, u1.lastName, a.addressDetails, h.addressId, w.name, d.name, p.name,a.wardId,a.districtId,a.provinceId, t.typeName, h.lastModifiedDate, u2.firstName, u2.lastName, u.firstName, u.lastName) from HousesEntity h " +
            "join TypeHouseEntity t on h.typeHouseId = t.typeId " +
            "left join UserEntity u on h.userId = u.userId " +
            "left join UserEntity u1 on h.createdBy = u1.userId " +
            "left join UserEntity u2 on h.lastModifiedBy = u2.userId " +
            "left join AddressEntity a on h.addressId = a.addressId " +
            "left join ProvinceEntity p on a.provinceId = p.provinceId " +
            "left join DistrictEntity d on a.districtId = d.districtId " +
            "left join WardEntity w on w.wardId = a.wardId " +
            "where h.houseId = ?1 ")

    HouseManagerTypeVo findHouseById(Integer id);

    @Query(value = "select * from room_finding_system.houses order by houseid DESC LIMIT 1",nativeQuery = true)

    HousesEntity getLastHouse();

}
