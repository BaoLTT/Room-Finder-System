package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.RoomDtoN;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;


import jakarta.persistence.Tuple;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@SpringBootApplication
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query("select r from RoomEntity r")
    List<RoomEntity> findAllRooms();


    @Query("SELECT r FROM RoomEntity r WHERE r.roomId = :roomId")
    RoomEntity getRoomById(int roomId);

    @Query("SELECT i FROM RoomImagesEntity i WHERE i.roomId = :roomId")
    List<RoomImagesEntity> getImageByRoomId(int roomId);

    @Query("SELECT i FROM HouseImagesEntity i join HousesEntity h on h.houseId = i.houseId join RoomEntity r on h.houseId = r.houseId WHERE r.roomId = :roomId")
    List<HouseImagesEntity> getImageHouseByRoomId(int roomId);

    @Query("SELECT s\n" +
            "FROM ServiceDetailEntity s\n" +
            "JOIN ServiceRoomEntity rs ON s.serviceId = rs.serviceId\n" +
            "WHERE rs.roomId = :roomId")
    List<ServiceDetailEntity> getServiceByRoomId(int roomId);



    //homepage

    @Query(value = "SELECT r.roomid, r.room_name, h.house_name , t.type_name, r.price, (SELECT GROUP_CONCAT(i.image_link) FROM room_images i WHERE i.roomid = r.roomid) AS Image_Link, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, r.area\n" +
            "            FROM room r\n" +
            "            LEFT JOIN room_type t on r.room_type = t.typeid\n" +
            "            LEFT JOIN houses h ON r.houseid = h.houseid \n" +
            "            LEFT JOIN address a ON h.addressid = a.addressid \n" +
            "            LEFT JOIN province p ON a.provinceid = p.provinceid \n" +
            "            LEFT JOIN district d ON a.districtid = d.districtid \n" +
            "            LEFT JOIN ward w ON a.wardid = w.wardid \n" +
            "            WHERE r.statusid = 1" +
            "\t\t\tGROUP BY r.roomid, r.room_name, h.house_name , t.type_name, r.price, a.address_details, r.area ORDER BY\n" +
            "    RAND() LIMIT 8 OFFSET 0", nativeQuery = true)
    List<Tuple> viewRoomInHome();


    //room_type list trong boarding house details
    @Query(value = "SELECT r.roomid, t.typeid, t.type_name,(select group_concat(r1.roomid) from room r1 where r1.houseid = h.houseid and r1.room_type = r.room_type\n" +
            "            group by h.houseid, r.room_type) as room_list, h.houseid, h.house_name,r.price, (select group_concat(sd1.service_name) from service_detail sd1 \n" +
            "            join service_room sc1 on sd1.serviceid = sc1.serviceid where sc1.roomid =  r.roomid\n" +
            "            group by  r.roomid, h.houseid) as service_list\n" +
            "            from room r \n" +
            "            join room_type t on r.room_type = t.typeid \n" +
            "            left join houses h on r.houseid = h.houseid\n" +
            "            left join service_room sr on r.roomid = sr.roomid\n" +
            "            left join service_detail sd on sr.serviceid = sd.serviceid\n" +
            "            where r.houseid = ?1\n" +
            "            group by r.roomid, t.typeid, t.type_name, h.houseid, h.house_name, r.price; ", nativeQuery = true)
    List<Tuple> viewRoomInHouseDetail(int houseId);

    @Query(value="select r.room_name from room r where r.roomid = ?1", nativeQuery = true)
    String getRoomNameById(String id);

    @Query(value = "select r.roomid, r.room_name,h.house_name,r.price,rt.type_name,\n" +
            "            (select group_concat(i.image_link) from room_images i where i.roomid=r.roomid) as images from room r join houses h \n" +
            "            on r.houseid=h.houseid join room_type rt on rt.typeid = r.room_type where r.statusid=1 \n" +
            "            and r.price BETWEEN ?1 AND ?2 AND (r.room_name like '%' ?3 '%' or h.house_name like '%' ?3 '%') and r.room_type IN ?4 \n" +
            "            GROUP BY r.roomid, r.room_name, h.house_name, r.price, rt.type_name LIMIT ?6 OFFSET ?5", nativeQuery = true)
    List<Tuple> getRoomList(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);

    @Query(value = "SELECT COUNT(*) from (select r.roomid, r.room_name,h.house_name,r.price,rt.type_name, " +
            "                        (select group_concat(i.image_link) from room_images i where i.roomid=r.roomid) as images from room r join houses h \n" +
            "                        on r.houseid=h.houseid join room_type rt on rt.typeid = r.room_type where r.statusid=1 \n" +
            "                        and r.price BETWEEN ?1 AND ?2 AND r.room_name like '%' ?3 '%' and r.room_type IN ?4 \n" +
            "                        GROUP BY r.roomid, r.room_name, h.house_name, r.price, rt.type_name) as subquery",nativeQuery = true)
    int countRoom(int min, int max, String roomName, List<Integer> type);



    @Query(value = "select r.roomId ,h.house_name ,r.room_name, concat(u.last_name,' ',u.first_name) as full_name, r.statusid, r.status_update_date from room r join houses h on h.houseid = r.houseid\n" +
            "left join user u on h.userid = u.userid", nativeQuery = true)
    List<Tuple> getRoomStatusInAdminDashboard();

    @Query("SELECT r FROM RoomEntity r inner join HousesEntity h ON r.houseId = h.houseId " +
            "WHERE h.houseId = :houseId and r.roomName = :name")
    RoomEntity getRoomByHouseIdAndName(String name, Integer houseId);

    @Query("select new com.roomfindingsystem.dto.RoomDto(r.roomId, r.roomName, t.typeName, r.description, r.price, h.houseName, r.area, case when r.statusId = 1 then 'ACTIVE' else 'INACTIVE' end) " +
            "from RoomEntity r " +
            "join HousesEntity h on r.houseId = h.houseId " +
            "left join RoomTypeEntity t on t.typeId = r.roomType ")
    List<RoomDto> findRoomsDetail();

    @Query(value = "SELECT r.roomid, r.room_name, h.house_name, t.type_name, r.price, (SELECT GROUP_CONCAT(i.image_link) FROM room_images i WHERE i.roomid = r.roomid) AS Image_Link, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, r.area\n" +
            "FROM room r\n" +
            "LEFT JOIN room_type t ON r.room_type = t.typeid\n" +
            "LEFT JOIN houses h ON r.houseid = h.houseid\n" +
            "LEFT JOIN address a ON h.addressid = a.addressid\n" +
            "LEFT JOIN province p ON a.provinceid = p.provinceid\n" +
            "LEFT JOIN district d ON a.districtid = d.districtid\n" +
            "LEFT JOIN ward w ON a.wardid = w.wardid\n" +
            "WHERE r.statusid = 1\n" +
            "GROUP BY r.roomid, r.room_name, h.house_name, t.type_name, r.price, a.address_details, r.area\n" +
            "ORDER BY ABS(r.price - :targetPrice) ASC\n" +
            "LIMIT 4 OFFSET 0", nativeQuery = true)
    List<Tuple> findRoomsNearPrice(@Param("targetPrice") BigDecimal targetPrice);







    @Query("select new com.roomfindingsystem.dto.RoomDto(r.roomId, r.roomName, t.typeName, r.description, r.price, h.houseName, r.area, case when r.statusId = 1 then 'ACTIVE' else 'INACTIVE' end) " +
            "from RoomEntity r " +
            "join HousesEntity h on r.houseId = h.houseId " +
            "left join RoomTypeEntity t on t.typeId = r.roomType "+
            "WHERE h.houseId = :houseId")
    List<RoomDto> findRoomsInHouse(int houseId);

    @Query("select count(*) from RoomEntity r where r.houseId = ?1")
    int countRoomEntityByHouseId(int id);
}
