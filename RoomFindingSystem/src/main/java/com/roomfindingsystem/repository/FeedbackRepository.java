package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.FeedbackHomeDto;
import com.roomfindingsystem.entity.FeedbackEntity;


import jakarta.transaction.Transactional;
import com.roomfindingsystem.dto.FeedbackDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Integer> {


    @Query("SELECT NEW com.roomfindingsystem.dto.FeedbackDto(f.feedbackId, f.title, f.content, f.createdDate, u.firstName, u.lastName, u.imageLink, u.userId) FROM FeedbackEntity f JOIN UserEntity u " +
            "ON u.userId = f.memberId WHERE f.houseId = :houseId")
    List<FeedbackDto> findFeedbackDtosByHouseId(int houseId);

    @Query("SELECT f FROM FeedbackEntity f WHERE f.houseId = :houseId AND f.memberId = :memberId")
    List<FeedbackEntity> getFeedbackEntityByUid(int houseId, int memberId);

    public FeedbackEntity save(FeedbackEntity feedbackEntity);
    @Transactional
    @Modifying
    @Query("DELETE FROM FeedbackEntity f WHERE f.houseId = :houseId AND f.memberId = :memberId")
    void deleteByHouseIdAndMemberId(int houseId, int memberId);


//    @Query("UPDATE FeedbackEntity f SET f.someField = :newValue WHERE f.houseId = :houseId AND f.memberId = :memberId")
//    void updateFeedbackEntity(int houseId, int memberId, String newValue);











    @Query("select new com.roomfindingsystem.dto.FeedbackHomeDto(u.firstName, u.lastName, h.houseName, f.content, h.houseId,  p.name, d.name, w.name, a.addressDetails) from FeedbackEntity f left join HousesEntity h on f.houseId = h.houseId " +
            "left join UserEntity u on f.memberId = u.userId " +
            "left join AddressEntity a on h.addressId = a.addressId " +
            "left join ProvinceEntity p on a.provinceId = p.provinceId " +
            "left join DistrictEntity d on d.provinceId= p.provinceId " +
            "left join WardEntity w on w.districtId= d.districtId ")
    List<FeedbackHomeDto> viewTop4Home();
}
