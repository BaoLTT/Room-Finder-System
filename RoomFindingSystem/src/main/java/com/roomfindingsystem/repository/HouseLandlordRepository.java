package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.HousesEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("HoseLandlordRepository")
public interface HouseLandlordRepository extends JpaRepository<HousesEntity,Integer> {
    @Query(value = "SELECT \n" +
            "    h.houseid, \n" +
            "    h.house_name, \n" +
            "    t.type_name, \n" +
            "    a.address_details,\n" +
            "    a.addressid,\n" +
            "    h.type_houseid,\n" +
            "    h.description,\n" +
            "    h.created_date,\n" +
            "    u.first_name,\n" +
            "    u.last_name,\n" +
            "    w.name AS ward_name, \n" +
            "    d.name AS district_name, \n" +
            "    p.name AS province_name,\n" +
            "\ta.wardid AS wardID ,\n" +
            "    a.districtid AS districtID,\n" +
            "    a.provinceid AS provinceID,\n" +
            "    u1.first_name AS last_modified_byFirstName,\n" +
            "    u1.last_name AS last_modified_byLastName,\n" +
            "    u2.first_name AS userFirstName,\n" +
            "    u2.last_name AS userLastName,\n" +
            "    h.status,\n" +
            "    (SELECT MIN(r.price) FROM room r WHERE r.houseid = h.houseid) AS minPrice, \n" +
            "    (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link,\n" +
            "    SUBSTRING_INDEX((SELECT GROUP_CONCAT(DISTINCT sd.service_name) FROM service_house sh\n" +
            "        LEFT JOIN service_detail sd ON sd.serviceid = sh.serviceid\n" +
            "        WHERE sh.houseid = h.houseid), ',', 3) AS Service_Name,\n" +
            "    h.last_modified_date, \n" +
            "    (SELECT COUNT(roomid) FROM room r WHERE r.houseid = h.houseid) AS count_Rooms,\n" +
            "    (SELECT COUNT(likeid) FROM room_finding_system.like l WHERE l.houseid = h.houseid) AS like_House\n" +
            "FROM houses h \n" +
            "JOIN type_house t ON h.type_houseid = t.typeid \n" +
            "LEFT JOIN room r ON r.houseid = h.houseid \n" +
            "LEFT JOIN user u ON u.userid = h.created_by\n" +
            " LEFT JOIN user u1 ON u1.userid = h.last_modified_by\n" +
            " LEFT JOIN user u2 ON u2.userid = h.userid\n" +
            "LEFT JOIN address a ON h.addressid = a.addressid \n" +
            "LEFT JOIN province p ON a.provinceid = p.provinceid \n" +
            "LEFT JOIN district d ON a.districtid = d.districtid \n" +
            "LEFT JOIN ward w ON a.wardid = w.wardid \n" +
            "WHERE \n" +
            "    h.userid = ?1 \n" +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date ",nativeQuery = true)
    List<Tuple> findHouseByUser(int userId);

    @Query(value = "SELECT \n" +
            "    h.houseid, \n" +
            "    h.house_name, \n" +
            "    t.type_name, \n" +
            "    a.address_details,\n" +
            "    a.addressid,\n" +
            "    h.type_houseid,\n" +
            "    h.description,\n" +
            "    h.created_date,\n" +
            "    u.first_name,\n" +
            "    u.last_name,\n" +
            "    w.name AS ward_name, \n" +
            "    d.name AS district_name, \n" +
            "    p.name AS province_name,\n" +
            "\ta.wardid AS wardID ,\n" +
            "    a.districtid AS districtID,\n" +
            "    a.provinceid AS provinceID,\n" +
            "    u1.first_name AS last_modified_byFirstName,\n" +
            "    u1.last_name AS last_modified_byLastName,\n" +
            "    u2.first_name AS userFirstName,\n" +
            "    u2.last_name AS userLastName,\n" +
            "    h.status,\n" +
            "    (SELECT MIN(r.price) FROM room r WHERE r.houseid = h.houseid) AS minPrice, \n" +
            "    (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link,\n" +
            "    (SELECT GROUP_CONCAT(DISTINCT sd.serviceid) FROM service_house sh\n" +
            "        LEFT JOIN service_detail sd ON sd.serviceid = sh.serviceid\n" +
            "        WHERE sh.houseid = h.houseid) AS Service_Name,\n" +
            "    h.last_modified_date, \n" +
            "    (SELECT COUNT(roomid) FROM room r WHERE r.houseid = h.houseid) AS count_Rooms,\n" +
            "    (SELECT COUNT(likeid) FROM room_finding_system.like l WHERE l.houseid = h.houseid) AS like_House\n" +
            "FROM houses h \n" +
            "JOIN type_house t ON h.type_houseid = t.typeid \n" +
            "LEFT JOIN room r ON r.houseid = h.houseid \n" +
            "LEFT JOIN user u ON u.userid = h.created_by\n" +
            " LEFT JOIN user u1 ON u1.userid = h.last_modified_by\n" +
            " LEFT JOIN user u2 ON u2.userid = h.userid\n" +
            "LEFT JOIN address a ON h.addressid = a.addressid \n" +
            "LEFT JOIN province p ON a.provinceid = p.provinceid \n" +
            "LEFT JOIN district d ON a.districtid = d.districtid \n" +
            "LEFT JOIN ward w ON a.wardid = w.wardid \n" +
            "WHERE \n" +
            "    h.houseid = ?1 \n" +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date ",nativeQuery = true)
    Tuple findHouseByID(int houseid);

    @Query(value = "SELECT \n" +
            "    h.houseid, \n" +
            "    h.house_name, \n" +
            "    t.type_name, \n" +
            "    a.address_details,\n" +
            "    a.addressid,\n" +
            "    h.type_houseid,\n" +
            "    h.description,\n" +
            "    h.created_date,\n" +
            "    u.first_name,\n" +
            "    u.last_name,\n" +
            "    w.name AS ward_name, \n" +
            "    d.name AS district_name, \n" +
            "    p.name AS province_name,\n" +
            "\ta.wardid AS wardID ,\n" +
            "    a.districtid AS districtID,\n" +
            "    a.provinceid AS provinceID,\n" +
            "    u1.first_name AS last_modified_byFirstName,\n" +
            "    u1.last_name AS last_modified_byLastName,\n" +
            "    u2.first_name AS userFirstName,\n" +
            "    u2.last_name AS userLastName,\n" +
            "    h.status,\n" +
            "    (SELECT MIN(r.price) FROM room r WHERE r.houseid = h.houseid) AS minPrice, \n" +
            "    (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link,\n" +
            "    (SELECT GROUP_CONCAT(DISTINCT sd.serviceid) FROM service_house sh\n" +
            "        LEFT JOIN service_detail sd ON sd.serviceid = sh.serviceid\n" +
            "        WHERE sh.houseid = h.houseid) AS Service_Name,\n" +
            "    h.last_modified_date, \n" +
            "    (SELECT COUNT(roomid) FROM room r WHERE r.houseid = h.houseid) AS count_Rooms,\n" +
            "    (SELECT COUNT(likeid) FROM room_finding_system.like l WHERE l.houseid = h.houseid) AS like_House\n" +
            "FROM houses h \n" +
            "JOIN type_house t ON h.type_houseid = t.typeid \n" +
            "LEFT JOIN room r ON r.houseid = h.houseid \n" +
            "LEFT JOIN user u ON u.userid = h.created_by\n" +
            " LEFT JOIN user u1 ON u1.userid = h.last_modified_by\n" +
            " LEFT JOIN user u2 ON u2.userid = h.userid\n" +
            "LEFT JOIN address a ON h.addressid = a.addressid \n" +
            "LEFT JOIN province p ON a.provinceid = p.provinceid \n" +
            "LEFT JOIN district d ON a.districtid = d.districtid \n" +
            "LEFT JOIN ward w ON a.wardid = w.wardid \n" +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date ",nativeQuery = true)
    List<Tuple> getAllHouse();
}
