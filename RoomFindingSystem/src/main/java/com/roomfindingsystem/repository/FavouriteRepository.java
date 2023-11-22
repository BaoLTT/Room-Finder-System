package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity,Integer> {

    FavouriteEntity save(FavouriteEntity favouriteEntity);


    @Query("select new com.roomfindingsystem.dto.FavouriteDto(h.houseId,h.houseName,t.typeName, ad.addressDetails, pr.name,d.name,w.name) from FavouriteEntity f " +
            "join HousesEntity h on f.houseId = h.houseId " +
            "join TypeHouseEntity t on h.typeHouseId = t.typeId " +
            "join AddressEntity ad on h.addressId = ad.addressId " +
            "join ProvinceEntity pr on ad.provinceId = pr.provinceId " +
            "join DistrictEntity d on ad.districtId = d.districtId " +
            "join  WardEntity w on ad.wardId = w.wardId " +
            "order by f.createdDate desc ")
    List<FavouriteDto> findAllFavourite();
    @Transactional
    @Modifying
    void deleteFavouriteEntitiesByHouseId(int houseid);

    Optional<FavouriteEntity> getAllByHouseId(int houseid);
}
