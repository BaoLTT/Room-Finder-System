package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.RoomDtoN;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;


import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT DISTINCT r.floor FROM RoomEntity r ORDER BY r.floor ASC")
    List<String> findAllDistinctFloors();

    @Query("SELECT i FROM RoomImagesEntity i WHERE i.roomId = :roomId")
    List<RoomImagesEntity> getImageByRoomId(int roomId);

    @Query("SELECT i FROM HouseImagesEntity i join HousesEntity h on h.houseId = i.houseId join RoomEntity r on h.houseId = r.houseId WHERE r.roomId = :roomId")
    List<HouseImagesEntity> getImageHouseByRoomId(int roomId);

    @Query("SELECT s\n" +
            "FROM ServiceDetailEntity s\n" +
            "JOIN ServiceRoomEntity rs ON s.serviceId = rs.serviceId\n" +
            "WHERE rs.roomId = :roomId")
    List<ServiceDetailEntity> getServiceByRoomId(int roomId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `room_finding_system`.`room`\n" +
            "WHERE room.houseid = ?1 ;",nativeQuery = true)
    void deleteByHouseId(int houseid);



    @Query("SELECT s\n" +
            "FROM ServiceDetailEntity s\n" +
            "JOIN ServiceRoomEntity rs ON s.serviceId = rs.serviceId\n" +
            "WHERE rs.roomId = :roomId\n" +
            "UNION\n" +
            "SELECT s\n" +
            "FROM ServiceDetailEntity s\n" +
            "JOIN ServiceHouseEntity hs ON s.serviceId = hs.serviceId\n" +
            "JOIN HousesEntity h ON h.houseId = hs.houseId\n" +
            "JOIN RoomEntity r ON r.houseId = h.houseId\n" +
            "WHERE r.roomId = :roomId")
    List<ServiceDetailEntity> getAllServicesByRoomId(int roomId);




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
    @Query(value = "SELECT r.roomid, h.houseid, r.floor, t.type_name ,r.room_type,r.room_name, h.house_name,r.price, r.statusid\n" +
            "            from room r \n" +
            "            join room_type t on r.room_type = t.typeid \n" +
            "            left join houses h on r.houseid = h.houseid\n" +
            "            left join service_room sr on r.roomid = sr.roomid\n" +
            "            left join service_detail sd on sr.serviceid = sd.serviceid\n" +
            "            where r.houseid = ?1\n" +
            "            group by r.roomid, t.typeid, t.type_name, h.houseid, h.house_name, r.price;", nativeQuery = true)
    List<Tuple> viewRoomInHouseDetail(int houseId);

    @Query(value="select r.room_name from room r where r.roomid = ?1", nativeQuery = true)
    String getRoomNameById(String id);

    @Query(value = "select r.roomid, r.room_name,h.house_name,r.price,rt.type_name,\n" +
            "            (select group_concat(i.image_link) from room_images i where i.roomid=r.roomid) as images, r.floor from room r join houses h \n" +
            "            on r.houseid=h.houseid join room_type rt on rt.typeid = r.room_type where r.statusid=1 \n" +
            "            and ((r.price BETWEEN ?1 AND ?2) or (r.price BETWEEN ?3 AND ?4) or (r.price BETWEEN ?5 AND ?6)) AND (r.room_name like '%' ?7 '%' or h.house_name like '%' ?7 '%') and r.room_type IN ?8 and r.floor IN ?11 \n" +
            "            GROUP BY r.roomid, r.room_name, h.house_name, r.price, rt.type_name, r.floor LIMIT ?10 OFFSET ?9", nativeQuery = true)
    List<Tuple> getRoomList(int min1, int max1, int min2, int max2, int min3, int max3, String roomName, List<Integer> type, int pageIndex, int pageSize, List<Integer> floor);

    @Query(value = "SELECT COUNT(*) from (select r.roomid, r.room_name,h.house_name,r.price,rt.type_name, " +
            "                        (select group_concat(i.image_link) from room_images i where i.roomid=r.roomid) as images, r.floor from room r join houses h \n" +
            "                        on r.houseid=h.houseid join room_type rt on rt.typeid = r.room_type where r.statusid=1 \n" +
            "                        and ((r.price BETWEEN ?1 AND ?2) or (r.price BETWEEN ?3 AND ?4) or (r.price BETWEEN ?5 AND ?6)) AND (r.room_name like '%' ?7 '%' or h.house_name like '%' ?7 '%') and r.room_type IN ?8 and r.floor IN ?9 \n" +
            "                        GROUP BY r.roomid, r.room_name, h.house_name, r.price, rt.type_name, r.floor) as subquery",nativeQuery = true)
    int countRoom(int min1, int max1, int min2, int max2, int min3, int max3, String roomName, List<Integer> type, List<Integer> floor);



    @Query(value = "select r.roomId ,h.house_name ,r.room_name, concat(u.last_name,' ',u.first_name) as full_name, r.statusid, r.status_update_date from room r join houses h on h.houseid = r.houseid\n" +
            "left join user u on h.userid = u.userid where r.statusid=1 order by r.status_update_date desc", nativeQuery = true)
    List<Tuple> getRoomStatusInAdminDashboard();

    @Query("SELECT r FROM RoomEntity r inner join HousesEntity h ON r.houseId = h.houseId " +
            "WHERE h.houseId = :houseId and r.roomName = :name")
    RoomEntity getRoomByHouseIdAndName(String name, Integer houseId);

    @Query("select new com.roomfindingsystem.dto.RoomDto(r.roomId, r.roomName,r.floor, t.typeName, r.description, r.price, h.houseName, r.area, case when r.statusId = 1 then 'ACTIVE' else 'INACTIVE' end) " +
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

    @Query
    RoomEntity findRoomEntityByRoomNameAndHouseId(String roomName, int houseId);



    @Query("select new com.roomfindingsystem.dto.RoomDto(r.roomId, r.roomName,r.floor, t.typeName, r.description, r.price, h.houseName, r.area, case when r.statusId = 1 then 'ACTIVE' else 'INACTIVE' end) " +
            "from RoomEntity r " +
            "join HousesEntity h on r.houseId = h.houseId " +
            "left join RoomTypeEntity t on t.typeId = r.roomType "+
            "WHERE h.houseId = ?1 ")
    List<RoomDto> findRoomsInHouse(int houseId);

    @Query("select count(*) from RoomEntity r where r.houseId = ?1")
    int countRoomEntityByHouseId(int id);
    @Query("select count(*) from RoomEntity r where r.statusId = 1")
    int countEmptyRoom();

    @Query("select count(*) from RoomEntity r where r.statusId = 0")
    int countInhabitedRoom();
}
