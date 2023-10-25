package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.vo.HouseDto;
import com.roomfindingsystem.vo.HouseImageLink;
import com.roomfindingsystem.vo.ServiceDto;
import jakarta.persistence.Tuple;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("houseRepository")

@SpringBootApplication
public interface HouseRepository extends JpaRepository<HousesEntity,Integer> {

    @Query(value = "SELECT h.houseid, h.house_name, t.type_name, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, MIN(r.price) AS minPrice, (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link, h.last_modified_date " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "WHERE r.price BETWEEN ?1 AND ?2 AND h.house_name like '%' ?3 '%' and h.type_houseid IN ?4 " +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date LIMIT ?6 OFFSET ?5 ",nativeQuery=true )
    List<Tuple> findHouse(int min, int max, String houseName, List<Integer> type, int pageIndex, int pageSize);

    @Query(value = "SELECT COUNT(*) FROM (SELECT h.houseid, h.house_name, t.type_name, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, MIN(r.price) AS minPrice, (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link, h.last_modified_date " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "WHERE r.price BETWEEN ?1 AND ?2 AND h.house_name like '%' ?3 '%' and h.type_houseid IN ?4 " +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_date) as subquery",nativeQuery=true )
    int countHouse(int min, int max, String houseName, List<Integer> type);

    @Query("SELECT new com.roomfindingsystem.vo.HouseDto( h.houseId, h.houseName,h.description,h.createdDate, u.lastName,u.firstName , u.phone,a.addressId, t.typeName )\n" +
            "FROM HousesEntity as h \n" +
            "left join UserEntity as u on h.userId = u.userId \n" +
            "left join AddressEntity as a on h.addressId = a.addressId\n" +
            "left join TypeHouseEntity as t on t.typeId = h.typeHouseId\n" +
            " where h.houseId=?1")
    List<HouseDto> findAllDetail(int houseId);
    @Query("SELECT new com.roomfindingsystem.vo.HouseImageLink(i.imageLink) FROM HouseImagesEntity i join HousesEntity h  on i.houseId = h.houseId where h.houseId=?1")
    List<HouseImageLink> getByHouseImageid(int houseId);
    @Query("select new com.roomfindingsystem.vo.ServiceDto(shd.serviceName,shd.description) from ServiceHouseEntity sh join ServiceDetailEntity shd on sh.serviceId=shd.serviceId where sh.houseId=?1")
    List<ServiceDto> getServiceById(int houseId);

    @Query("SELECT h from HousesEntity h join RoomEntity r on r.houseId = h.houseId where r.roomId=:roomid")
    HousesEntity findHouseByRoomId(int roomid);



    //homepage
    @Query(value = "SELECT h.houseid, h.house_name, t.type_name, a.address_details, w.name AS ward_name, d.name AS district_name, p.name AS province_name, MIN(r.price) AS minPrice, (SELECT GROUP_CONCAT(i.image_link) FROM house_images i WHERE i.houseid = h.houseid) AS Image_Link, h.last_modified_by " +
            "FROM houses h " +
            "JOIN type_house t ON h.type_houseid = t.typeid " +
            "LEFT JOIN room r ON r.houseid = h.houseid " +
            "LEFT JOIN address a ON h.addressid = a.addressid " +
            "LEFT JOIN province p ON a.provinceid = p.provinceid " +
            "LEFT JOIN district d ON a.districtid = d.districtid " +
            "LEFT JOIN ward w ON a.wardid = w.wardid " +
            "GROUP BY h.houseid, h.house_name, t.type_name, a.address_details, ward_name, district_name, province_name, h.last_modified_by LIMIT 8 OFFSET 0 ",nativeQuery=true )
    List<Tuple> viewHouseInHome();


}
