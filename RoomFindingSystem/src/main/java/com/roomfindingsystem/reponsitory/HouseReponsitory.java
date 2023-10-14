package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.HouseTypeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("houseRepository")
public interface HouseReponsitory extends JpaRepository<HousesEntity,Integer> {

    @Query("select new com.roomfindingsystem.vo.HouseTypeVo(h.houseId, h.houseName, t.typeName, a.addressDetails, w.name, d.name, p.name, MIN(r.price), h.lastModifiedDate) from HousesEntity h " +
            "join TypeHouseEntity t on h.typeHouseId = t.typeId " +
            "left join RoomEntity r on r.houseId = h.houseId " +
            "left join AddressEntity a on h.addressId = a.addressId " +
            "left join ProvinceEntity p on a.provinceId = p.provinceId " +
            "left join DistrictEntity d on a.districtId = d.districtId " +
            "left join WardEntity w on w.wardId = a.wardId " +
            "group by h.houseId, h.houseName, t.typeName, a.addressDetails, w.name, p.name, r.price, h.lastModifiedDate")
    List<HouseTypeVo> getAllHouse();
    @Query("select new com.roomfindingsystem.vo.HouseTypeVo(h.houseId, h.houseName, t.typeName, a.addressDetails, w.name, d.name, p.name, MIN(r.price), h.lastModifiedDate) from HousesEntity h " +
            "join TypeHouseEntity t on h.typeHouseId = t.typeId " +
            "left join RoomEntity r on r.houseId = h.houseId " +
            "left join AddressEntity a on h.addressId = a.addressId " +
            "left join ProvinceEntity p on a.provinceId = p.provinceId " +
            "left join DistrictEntity d on a.districtId = d.districtId " +
            "left join WardEntity w on w.wardId = a.wardId " +
            "where (r.price >= ?1 and r.price<= ?2 ) and h.houseName like ?3 and h.typeHouseId IN  (?4) " +
            "group by h.houseId, h.houseName, t.typeName, a.addressDetails, w.name, p.name, h.lastModifiedDate")
    Page<HouseTypeVo> findHouse(int min,int max, String houseName,List<Integer> type, Pageable pageable);

    @Query("SELECT new com.roomfindingsystem.vo.HouseDto( h.houseId, h.houseName,h.description,h.createdDate, u.lastName,u.firstName , u.phone, shd.serviceName,a.addressId, t.typeName )\n" +
            "FROM HousesEntity as h \n" +
            "left join UserEntity as u on h.userId = u.userId \n" +
            "left join AddressEntity as a on h.addressId = a.addressId\n" +
            " left join ServiceHouseEntity as sh on h.serviceId = sh.serviceId\n" +
            "left join ServiceDetailEntity as shd on sh.serviceId= shd.serviceId\n" +
            "left join TypeHouseEntity as t on t.typeId = h.typeHouseId\n" +
            " where h.houseId=2")
    List<HouseDto> findAllDetail();
    @Query("SELECT new com.roomfindingsystem.vo.HouseImageLink(i.imageLink) FROM HouseImagesEntity i join HousesEntity h  on i.houseId = h.houseId where h.houseId=?1")
    List<HouseImageLink> getByHouseImageid(int houseId);

    @Query("SELECT h from HousesEntity h join RoomEntity r on r.houseId = h.houseId where r.roomId=:roomid")
    HousesEntity findHouseByRoomId(int roomid);

}
