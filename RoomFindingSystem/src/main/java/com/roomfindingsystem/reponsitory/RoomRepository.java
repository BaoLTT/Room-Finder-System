package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;

import com.roomfindingsystem.vo.RoomDto;
import com.roomfindingsystem.vo.RoomHomeVo;

import jakarta.persistence.Tuple;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
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

    @Query(value = "select r.roomid, r.room_name,h.house_name,r.price,rt.type_name,\n" +
            "            (select group_concat(i.image_link) from room_images i where i.roomid=r.roomid) as images from room r join houses h \n" +
            "            on r.houseid=h.houseid join room_type rt on rt.typeid = r.room_type where r.statusid=1 \n" +
            "            and r.price BETWEEN ?1 AND ?2 AND r.room_name like '%' ?3 '%' and r.room_type IN ?4 \n" +
            "            GROUP BY r.roomid, r.room_name, h.house_name, r.price, rt.type_name LIMIT ?6 OFFSET ?5", nativeQuery = true)
    List<Tuple> getRoomList(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);

    @Query(value = "SELECT COUNT(*) from room",nativeQuery = true)
    int countRoom();







}
