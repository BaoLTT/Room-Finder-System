package com.roomfindingsystem.reponsitory;

import com.roomfindingsystem.entity.FeedbackEntity;


import com.roomfindingsystem.vo.FeedbackDto;
import com.roomfindingsystem.vo.FeedbackHomeVo;
import com.roomfindingsystem.vo.FeedbackListVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Integer> {


    @Query("SELECT NEW com.roomfindingsystem.vo.FeedbackDto(f.feedbackId, f.title, f.content, f.createdDate, u.firstName, u.lastName, u.imageLink) FROM FeedbackEntity f JOIN UserEntity u " +
            "ON u.userId = f.memberId WHERE f.houseId = :houseId")
    List<FeedbackDto> findFeedbackDtosByHouseId(int houseId);





    @Query("select new com.roomfindingsystem.vo.FeedbackHomeVo(u.firstName, u.lastName, h.houseName, f.content, h.houseId,  p.name, d.name, w.name, a.name) from FeedbackEntity f left join HousesEntity h on f.houseId = h.houseId " +
            "left join UserEntity u on f.memberId = u.userId " +
            "left join AddressEntity a on h.addressId = a.addressId " +
            "left join ProvinceEntity p on a.provinceId = p.provinceId " +
            "left join DistrictEntity d on d.provinceId= p.provinceId " +
            "left join WardEntity w on w.districtId= d.districtId ")
    List<FeedbackHomeVo> viewTop4Home();

//    @Query(value = "select new com.roomfindingsystem.vo.FeedbackListVo(\n" +
//            "   f.feedbackId, \n" +
//            "   f.content,\n" +
//            "   f.title,\n" +
//            "   f.createdDate,\n" +
//            "   CASE\n" +
//            "      WHEN DATEDIFF(CURRENT_DATE(), f.createdDate) = 0 THEN 'Hôm nay'\n" +
//            "      WHEN DATEDIFF(CURRENT_DATE(), f.createdDate) = 1 THEN '1 ngày trước' \n" +
//            "      WHEN DATEDIFF(CURRENT_DATE(), f.createdDate) < 700 THEN CONCAT(DATEDIFF(CURRENT_DATE(), f.createdDate),' ngày trước')\n" +
//            "      ELSE DATE_FORMAT(f.createdDate, '%Y-%m-%d')\n" +
//            "   END,\n" +
//            " 'dayAgos'\n" +
//            ")\n" +
//            "from FeedbackEntity f\n" +
//            "ORDER BY f.createdDate DESC")
//    @Query(value = "SELECT NEW com.roomfindingsystem.vo.FeedbackListVo(f.feedbackId, f.content , f.title, f.createdDate) from FeedbackEntity f  ORDER BY f.createdDate DESC ")
    Page<FeedbackEntity> findAll(Pageable pageable);
}
