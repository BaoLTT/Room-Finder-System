package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.vo.RoomHomeVo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    @Query("SELECT r FROM RoomEntity r WHERE r.roomId = :roomId")
    RoomEntity getRoomById(int roomId);

    @Query("SELECT i FROM RoomImagesEntity i WHERE i.roomId = :roomId")
    List<RoomImagesEntity> getImageByRoomId(int roomId);

    @Query("SELECT s\n" +
            "FROM ServiceDetailEntity s\n" +
            "JOIN ServiceRoomEntity rs ON s.serviceId = rs.serviceId\n" +
            "WHERE rs.roomId = :roomId")
    List<ServiceDetailEntity> getServiceByRoomId(int roomId);


    //homepage
    @Query("select new com.roomfindingsystem.vo.RoomHomeVo(r.roomId, r.roomName, h.houseName,t.typeName, r.price ,p.name, d.name, w.name, a.name) from RoomEntity r join HousesEntity h on r.houseId = h.houseId " +
            "left join RoomTypeEntity t on t.typeId = r.roomType " +
            "left join AddressEntity a on h.addressId = a.addressId " +
            "left join ProvinceEntity p on a.provinceId = p.provinceId " +
            "left join DistrictEntity d on d.provinceId= p.provinceId " +
            "left join WardEntity w on w.districtId= d.districtId " +
            "order by r.createdDate desc")
    List<RoomHomeVo> viewTop4Home();
}
