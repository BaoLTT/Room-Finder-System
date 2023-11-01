package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;


import jakarta.persistence.Tuple;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "SELECT r.roomid, r.room_name, h.house_name , t.type_name, r.price, (SELECT GROUP_CONCAT(i.image_link) FROM room_images i WHERE i.roomid = r.roomid) AS Image_Link, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, r.area\n" +
            "            FROM room r\n" +
            "            LEFT JOIN room_type t on r.room_type = t.typeid\n" +
            "            LEFT JOIN houses h ON r.houseid = h.houseid \n" +
            "            LEFT JOIN address a ON h.addressid = a.addressid \n" +
            "            LEFT JOIN province p ON a.provinceid = p.provinceid \n" +
            "            LEFT JOIN district d ON a.districtid = d.districtid \n" +
            "            LEFT JOIN ward w ON a.wardid = w.wardid \n" +
            "\t\t\tGROUP BY r.roomid, r.room_name, h.house_name , t.type_name, r.price, a.address_details, r.area LIMIT 8 OFFSET 0", nativeQuery = true)
    List<Tuple> viewRoomInHome();


    //room_type list trong boarding house details
    @Query(value = "SELECT r.roomid, t.typeid, t.type_name,(select group_concat(r1.room_name) from room r1 where r1.houseid = h.houseid and r1.room_type = r.room_type\n" +
            "group by h.houseid, r.room_type) as room_list, h.houseid, h.house_name,r.price \n" +
            "from room r \n" +
            "join room_type t on r.room_type = t.typeid \n" +
            "left join houses h on r.houseid = h.houseid\n" +
            "where r.houseid = ?1 \n" +
            "group by r.roomid, t.typeid, t.type_name, h.houseid, h.house_name ; \n ", nativeQuery = true)
    List<Tuple> viewRoomInHouseDetail(int houseId);

    @Query(value = "select r.roomid, r.room_name,h.house_name,r.price,rt.type_name,\n" +
            "            (select group_concat(i.image_link) from room_images i where i.roomid=r.roomid) as images from room r join houses h \n" +
            "            on r.houseid=h.houseid join room_type rt on rt.typeid = r.room_type where r.statusid=1 \n" +
            "            and r.price BETWEEN ?1 AND ?2 AND r.room_name like '%' ?3 '%' and r.room_type IN ?4 \n" +
            "            GROUP BY r.roomid, r.room_name, h.house_name, r.price, rt.type_name LIMIT ?6 OFFSET ?5", nativeQuery = true)
    List<Tuple> getRoomList(int min, int max, String roomName, List<Integer> type, int pageIndex, int pageSize);

    @Query(value = "SELECT COUNT(*) from room",nativeQuery = true)
    int countRoom();







}
