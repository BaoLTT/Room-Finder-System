package com.roomfindingsystem.repository;


import com.roomfindingsystem.dto.HouseDto;
import com.roomfindingsystem.dto.HouseImageLink;
import com.roomfindingsystem.dto.ServiceDto;
import com.roomfindingsystem.entity.HousesEntity;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("houseRepository")

@SpringBootApplication
public interface HouseRepository extends JpaRepository<HousesEntity, Integer> {

    @Query(value = "SELECT h.houseid, h.house_name, t.type_name, a.address_details,h.star, w.name AS ward_name, d.name AS district_name, p.name AS province_name, (SELECT MIN(r.price) FROM room r WHERE r.houseid = h.houseid) AS minPrice," +
            " (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link," +
            " (SELECT GROUP_CONCAT(i.imageid) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Id," +
            "(SELECT GROUP_CONCAT(DISTINCT sd.service_name) FROM service_house sh" +
            "            LEFT JOIN service_detail sd ON sd.serviceid = sh.serviceid" +
            "            WHERE sh.houseid = h.houseid) AS Service_Name, h.last_modified_date," +
            "(select count(roomid) from room r where r.houseid = h.houseid AND r.statusid = 1 group by r.houseid)  as count_Room " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "WHERE (" +
            "        (r.price BETWEEN ?1 AND ?2 ) " +
            "        OR " +
            "        (r.price BETWEEN ?3 AND ?4 )" +
            "    )" +
            "    AND r.price = (SELECT MIN(price) FROM room r2 WHERE r2.houseid = h.houseid)" +
            "    AND h.status= 1 " +
            "    AND r.statusid= 1 " +
            " AND (h.house_name LIKE '%' ?5 '%' " +
            "        OR p.name LIKE '%' ?5 '%' " +
            "        OR d.name LIKE '%' ?5 '%' " +
            "        OR w.name LIKE '%' ?5 '%' " +
            "        OR a.address_details LIKE '%' ?5 '%' ) " +
            " AND h.type_houseid IN ?6 AND (" +
            "        SELECT COUNT(DISTINCT sh.serviceid) FROM service_house sh " +
            "        WHERE sh.houseid = h.houseid AND sh.serviceid IN ?7 LIMIT 2 ) = ?8 " +
            "GROUP BY h.houseid, h.house_name, h.star, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date " +
            "ORDER BY h.last_modified_date DESC" +
            " LIMIT ?10 OFFSET ?9 ", nativeQuery = true)
    List<Tuple> findHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service,int countService, int pageIndex, int pageSize);

    @Query(value = "SELECT COUNT(*) FROM (SELECT h.houseid, h.house_name, t.type_name, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name,h.star, (SELECT MIN(r.price) FROM room r WHERE r.houseid = h.houseid) AS minPrice," +
            " (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link," +
            " (SELECT GROUP_CONCAT(DISTINCT sd.service_name) FROM service_house sh" +
            "            LEFT JOIN service_detail sd ON sd.serviceid = sh.serviceid" +
            "            WHERE sh.houseid = h.houseid) AS Service_Name, h.last_modified_date," +
            "(select count(roomid) from room r where r.houseid = h.houseid AND r.statusid = 1 group by r.houseid)  as count_Room " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "WHERE (" +
            "        (r.price BETWEEN ?1 AND ?2 ) " +
            "        OR " +
            "        (r.price BETWEEN ?3 AND ?4 )" +
            "    )" +
            "    AND r.price = (SELECT MIN(price) FROM room r2 WHERE r2.houseid = h.houseid)" +
            "    AND h.status= 1 " +
            "    AND r.statusid= 1 " +
            " AND (h.house_name LIKE '%' ?5 '%' " +
            "        OR p.name LIKE '%' ?5 '%' " +
            "        OR d.name LIKE '%' ?5 '%' " +
            "        OR w.name LIKE '%' ?5 '%' " +
            "        OR a.address_details LIKE '%' ?5 '%' ) " +
            " AND h.type_houseid IN ?6 AND (" +
            "        SELECT COUNT(DISTINCT sh.serviceid) FROM service_house sh " +
            "        WHERE sh.houseid = h.houseid AND sh.serviceid IN ?7 LIMIT 2 ) = ?8 " +
            "GROUP BY h.houseid, h.house_name, h.star, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date " +
            "ORDER BY h.last_modified_date DESC " +
            ") as subquery", nativeQuery = true)
    int countHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service,int countService);

    @Query("SELECT new com.roomfindingsystem.dto.HouseDto( h.houseId, h.houseName,h.description,h.createdDate, u.lastName,u.firstName , u.phone,a.addressDetails, t.typeName ,p.name,d.name,w.name, h.star) FROM HousesEntity as h " +
            "left join UserEntity as u on h.userId = u.userId " +
            "left join AddressEntity as a on h.addressId = a.addressId " +
            "LEFT JOIN ProvinceEntity p ON a.provinceId = p.provinceId " +
            "LEFT JOIN DistrictEntity d ON a.districtId = d.districtId " +
            "LEFT JOIN WardEntity w ON a.wardId = w.wardId " +
            "left join TypeHouseEntity as t on t.typeId = h.typeHouseId " +
            " where h.houseId= ?1 ")
    List<HouseDto> findAllDetail(int houseId);

    @Query("SELECT new com.roomfindingsystem.dto.HouseImageLink(i.imageLink) FROM HouseImagesEntity i join HousesEntity h  on i.houseId = h.houseId where h.houseId=?1")
    List<HouseImageLink> getByHouseImageid(int houseId);

    @Query("select new com.roomfindingsystem.dto.ServiceDto(shd.serviceId,shd.serviceName,shd.description) from ServiceHouseEntity sh join ServiceDetailEntity shd on sh.serviceId=shd.serviceId where sh.houseId=?1")
    List<ServiceDto> getServiceById(int houseId);

    @Query("SELECT h from HousesEntity h join RoomEntity r on r.houseId = h.houseId where r.roomId=:roomid")
    HousesEntity findHouseByRoomId(int roomid);

    @Query("SELECT h from HousesEntity h where h.userId=:userId")
    List<HousesEntity> findHouseByMemberId(int userId);

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
            "WHERE h.status = 1 " +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_by  order by h.star desc", nativeQuery = true)
    List<Tuple> viewHouseInHome();

    @Query(value = "SELECT u.userid,h.houseId, h.house_name,th.type_name,ad.address_details,pr.name AS province_name,d.name AS district_name,w.name AS ward_name ,\n" +
            "(SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link, h.last_modified_date  \n" +
            "FROM room_finding_system.favourite f\n" +
            "join room_finding_system.user u on f.userid = u.userid\n" +
            "join room_finding_system.houses h on f.houseid = h.houseid\n" +
            "join room_finding_system.type_house th on h.type_houseid = th.typeid \n" +
            "join room_finding_system.address ad on h.addressid = ad.addressid \n" +
            "join room_finding_system.province pr on pr.provinceid = ad.provinceid\n" +
            "join room_finding_system.district d on  d.districtid = ad.districtid \n" +
            "join room_finding_system.ward w on ad.wardid = w.wardid where u.userid=?1  order by f.created_date desc;", nativeQuery = true)
    List<Tuple> viewHouseInHomeInFavourite(int id);

    //admin
    @Query("select count(*) from HousesEntity")
    int countHouses();

    @Transactional
    @Modifying
    @Query(value = "UPDATE room_finding_system.houses SET star = ?1 WHERE houseid = ?2", nativeQuery = true)
    void updateStarHouse(double star, Integer houseId);


//    @Query("select h from HousesEntity where houseId = ?1")
    HousesEntity getHousesEntitiesByHouseId(int id);









}
