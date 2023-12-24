package com.roomfindingsystem.repository;


import com.roomfindingsystem.dto.FeedbackDtoAdmin;
import com.roomfindingsystem.dto.FeedbackHomeDto;
import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.FeedbackEntity;



import jakarta.transaction.Transactional;
import com.roomfindingsystem.dto.FeedbackDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Integer> {



    @Query("SELECT NEW com.roomfindingsystem.dto.FeedbackDto(f.feedbackId, f.title, f.content, f.createdDate, u.firstName, u.lastName, u.imageLink, u.userId, f.star, f.status) FROM FeedbackEntity f JOIN UserEntity u " +
            "ON u.userId = f.memberId WHERE f.houseId = :houseId and f.status IN (:status)")
    List<FeedbackDto> findFeedbackDtosByHouseId(int houseId, List<Boolean> status);

    @Query(value = "SELECT f.feedbackid, f.title, f.content, f.created_date, u.first_name, u.last_name, u.image_link, u.userId, f.star, f.status FROM feedback f JOIN user u \n" +
            "            ON u.userId = f.memberId WHERE f.houseId = ?1 and f.status IN ?2 LIMIT ?4 OFFSET ?3", nativeQuery = true)
    List<FeedbackDto> findFeedbackDtosByHouseIdP(int houseId, List<Boolean> status, int pageIndex, int pageSize);

    @Query(value = "SELECT COUNT(f.feedbackid) FROM feedback f JOIN user u " +
            "ON u.userId = f.memberId WHERE f.houseId = ?1 and f.status IN ?2", nativeQuery = true)
    int countFeedbackDtosByHouseIdP(int houseId, List<Boolean> status);

//
//    @Query("SELECT COUNT(f) FROM FeedbackEntity f WHERE f.houseId = :houseId and f.status IN (:status)")
//    long countFeedbacksByHouseId(int houseId, List<Boolean> status);


    @Query("SELECT NEW com.roomfindingsystem.dto.FeedbackDtoAdmin(f.feedbackId, f.title, f.content, f.createdDate, u.firstName, u.lastName, u.imageLink, u.userId, f.star, f.status, h.houseName) FROM FeedbackEntity f JOIN UserEntity u " +
            "ON u.userId = f.memberId join HousesEntity h ON h.houseId = f.houseId  WHERE f.status IN (:status)")
    List<FeedbackDtoAdmin> findFeedbackDtos(List<Boolean> status);

    @Query("SELECT NEW com.roomfindingsystem.dto.FeedbackDto(f.feedbackId, f.title, f.content, f.createdDate, u.firstName, u.lastName, u.imageLink, u.userId, f.star, f.status) FROM FeedbackEntity f JOIN UserEntity u " +
            "ON u.userId = f.memberId WHERE f.houseId = :houseId and f.star = :star and f.status IN (:status)")
    List<FeedbackDto> findFeedbackDtosByHouseIdAndStar(int houseId, int star, List<Boolean> status);

    @Query("SELECT NEW com.roomfindingsystem.dto.FeedbackDtoAdmin(f.feedbackId, f.title, f.content, f.createdDate, u.firstName, u.lastName, u.imageLink, u.userId, f.star, f.status, h.houseName) FROM FeedbackEntity f JOIN UserEntity u " +
            "ON u.userId = f.memberId join HousesEntity h ON h.houseId = f.houseId WHERE f.star = :star and f.status IN (:status)")
    List<FeedbackDtoAdmin> findFeedbackDtosByStar(int star, List<Boolean> status);

    @Query("SELECT f FROM FeedbackEntity f WHERE f.houseId = :houseId AND f.memberId = :memberId")
    List<FeedbackEntity> getFeedbackEntityByUid(int houseId, int memberId);

    public FeedbackEntity save(FeedbackEntity feedbackEntity);
    @Transactional
    @Modifying
    @Query("DELETE FROM FeedbackEntity f WHERE f.houseId = :houseId AND f.memberId = :memberId")
    void deleteByHouseIdAndMemberId(int houseId, int memberId);

    @Transactional
    @Modifying
    @Query("DELETE FROM FeedbackEntity f WHERE f.houseId = :houseId")
    void deleteByHouseId(int houseId);

    @Transactional
    @Modifying
    @Query("UPDATE FeedbackEntity f SET f.status = false WHERE f.feedbackId = :feedbackId")
    void updateStatusToFalse(int feedbackId);

    @Transactional
    @Modifying
    @Query("UPDATE FeedbackEntity f SET f.status = true WHERE f.feedbackId = :feedbackId")
    void updateStatusToTrue(int feedbackId);




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
