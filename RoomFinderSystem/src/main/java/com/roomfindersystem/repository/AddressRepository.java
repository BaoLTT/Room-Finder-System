package com.roomfindersystem.repository;
import com.roomfindersystem.entity.AddressEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@SpringBootApplication
public interface AddressRepository extends CrudRepository<AddressEntity, Integer>, JpaRepository<AddressEntity, Integer> {
    Optional<AddressEntity> findByProvinceIdAndDistrictIdAndWardId(Integer provinceId, Integer districtId, Integer wardId);

    Optional<AddressEntity> findByProvinceIdAndDistrictIdAndWardIdAndAddressDetails(Integer provinceId, Integer districtId, Integer wardId, String addressDetails);
    @Query(value = "SELECT * FROM room_finding_system.address a where a.addressid = ?1 ",nativeQuery = true)
    AddressEntity getAddressById(Integer id);

    @Modifying
    @Query(value = "UPDATE room_finding_system.address " +
            "SET " +
            "address_details = ?1 ," +
            "districtid = ?2 ," +
            "name = ?3 ," +
            "provinceid = ?4 ," +
            "wardid = ?5 " +
            "WHERE addressid = ?6 ;",nativeQuery = true)
    void updateAddress(String addressDetails, Integer district, String name, Integer province, Integer Ward, Integer id);

}
