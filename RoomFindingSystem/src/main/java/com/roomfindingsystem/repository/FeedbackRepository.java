package com.roomfindingsystem.repository;


import com.roomfindingsystem.dto.FeedbackHomeDto;
import com.roomfindingsystem.dto.FeedbackListAdminDto;
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
            "left join DistrictEntity d on d.districtId= a.districtId " +
            "left join WardEntity w on w.wardId= a.wardId ")
    List<FeedbackHomeDto> viewTop4Home();
    @Query("select new com.roomfindingsystem.dto.FeedbackListAdminDto(f.feedbackId,f.content,f.createdDate,f.lastModifiedDate,f.title,h.houseName,u.lastName,u.email)from FeedbackEntity f " +
            " left join HousesEntity h on f.houseId= h.houseId" +
            " left join UserEntity u on f.memberId = u.userId")
    List<FeedbackListAdminDto> getFeedbackListForAdmin();

    @Query("select new com.roomfindingsystem.dto.FeedbackListAdminDto(f.feedbackId,f.content,f.createdDate,f.lastModifiedDate,f.title,h.houseName,u.lastName,u.email)from FeedbackEntity f " +
            " left join HousesEntity h on f.houseId= h.houseId" +
            " left join UserEntity u on f.memberId = u.userId " +
            "where h.createdBy =?1")
    List<FeedbackListAdminDto> getFeedbackListForLandLord( int createdBy);

}
