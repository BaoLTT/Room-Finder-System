package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.HousesEntity;


import com.roomfindingsystem.dto.HouseDto;
import com.roomfindingsystem.dto.HouseImageLink;
import com.roomfindingsystem.dto.ServiceDto;
import jakarta.persistence.Tuple;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("houseRepository")

@SpringBootApplication
public interface HouseRepository extends JpaRepository<HousesEntity,Integer> {

    @Query(value = "SELECT h.houseid, h.house_name, t.type_name, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, MIN(r.price) AS minPrice, (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link," +
            " SUBSTRING_INDEX( (SELECT GROUP_CONCAT(DISTINCT sd.service_name) FROM service_house sh" +
            "            LEFT JOIN service_detail sd ON sd.serviceid = sh.serviceid" +
            "            WHERE sh.houseid = h.houseid), ',', 2) AS Service_Name, h.last_modified_date," +
            "(select count(roomid) from room r where r.houseid = h.houseid group by r.houseid)  as count_Room, " +
            "(select count(likeid) from room_finding_system.like l where l.houseid = h.houseid group by l.houseid)  as like_House " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "WHERE r.price BETWEEN ?1 AND ?2 AND (h.house_name LIKE '%' ?3 '%' " +
            "        OR p.name LIKE '%' ?3 '%' " +
            "        OR d.name LIKE '%' ?3 '%' " +
            "        OR w.name LIKE '%' ?3 '%' " +
            "        OR a.address_details LIKE '%' ?3 '%' ) " +
            " AND h.type_houseid IN ?4 AND EXISTS (" +
            "        SELECT 1 FROM service_house sh" +
            "        WHERE sh.houseid = h.houseid AND sh.serviceid IN ?5 LIMIT 2 ) " +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date LIMIT ?7 OFFSET ?6 ",nativeQuery=true )
    List<Tuple> findHouse(int min, int max, String houseName, List<Integer> type, List<Integer> service, int pageIndex, int pageSize);

    @Query(value = "SELECT COUNT(*) FROM (SELECT h.houseid, h.house_name, t.type_name, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, MIN(r.price) AS minPrice, (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link," +
            " SUBSTRING_INDEX( (SELECT GROUP_CONCAT(DISTINCT sd.service_name) FROM service_house sh" +
            "            LEFT JOIN service_detail sd ON sd.serviceid = sh.serviceid" +
            "            WHERE sh.houseid = h.houseid), ',', 2) AS Service_Name, h.last_modified_date " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "WHERE r.price BETWEEN ?1 AND ?2 AND (h.house_name LIKE '%' ?3 '%' " +
            "        OR p.name LIKE '%' ?3 '%' " +
            "        OR d.name LIKE '%' ?3 '%' " +
            "        OR w.name LIKE '%' ?3 '%' " +
            "        OR a.address_details LIKE '%' ?3 '%' " +
            "    )  and h.type_houseid IN ?4 AND EXISTS (" +
            "        SELECT 1 FROM service_house sh" +
            "        WHERE sh.houseid = h.houseid AND sh.serviceid IN ?5 LIMIT 2" +
            "    ) " +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date) as subquery",nativeQuery=true )
    int countHouse(int min, int max, String houseName, List<Integer> type, List<Integer> service);

    @Query("SELECT new com.roomfindingsystem.dto.HouseDto( h.houseId, h.houseName,h.description,h.createdDate, u.lastName,u.firstName , u.phone,a.addressDetails, t.typeName ,p.name,d.name,w.name)\n" +
            "FROM HousesEntity as h \n" +
            "left join UserEntity as u on h.userId = u.userId \n" +
            "left join AddressEntity as a on h.addressId = a.addressId\n" +
            "LEFT JOIN ProvinceEntity p ON a.provinceId = p.provinceId " +
            "LEFT JOIN DistrictEntity d ON a.districtId = d.districtId " +
            "LEFT JOIN WardEntity w ON a.wardId = w.wardId " +
            "left join TypeHouseEntity as t on t.typeId = h.typeHouseId\n" +
            " where h.houseId=?1")
    List<HouseDto> findAllDetail(int houseId);
    @Query("SELECT new com.roomfindingsystem.dto.HouseImageLink(i.imageLink) FROM HouseImagesEntity i join HousesEntity h  on i.houseId = h.houseId where h.houseId=?1")
    List<HouseImageLink> getByHouseImageid(int houseId);
    @Query("select new com.roomfindingsystem.dto.ServiceDto(shd.serviceName,shd.description) from ServiceHouseEntity sh join ServiceDetailEntity shd on sh.serviceId=shd.serviceId where sh.houseId=?1")
    List<ServiceDto> getServiceById(int houseId);

    @Query("SELECT h from HousesEntity h join RoomEntity r on r.houseId = h.houseId where r.roomId=:roomid")
    HousesEntity findHouseByRoomId(int roomid);

    @Query(value = "SELECT houses.houseid FROM houses WHERE house_name = :name", nativeQuery = true)
    Integer findHousesEntityByHouseName(@Param("name") String name);

    //homepage
    @Query(value = "SELECT h.houseid, h.house_name, t.type_name, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link, h.last_modified_date, (select count(roomid) from room r where r.houseid = h.houseid and r.statusid = 1 group by r.houseid)  as count_Empty_Rooms, (select count(roomid) from room r where r.houseid = h.houseid group by r.houseid)  as count_Rooms " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
//            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_by LIMIT 6 OFFSET 0 ",nativeQuery=true )
    List<Tuple> viewHouseInHome();


}
