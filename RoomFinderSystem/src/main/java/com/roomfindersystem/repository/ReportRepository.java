package com.roomfindersystem.repository;


import com.roomfindersystem.dto.ReportDto;
import com.roomfindersystem.entity.FeedbackEntity;

import com.roomfindersystem.dto.ReportListDto;
import com.roomfindersystem.entity.ReportEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("reportRepository")
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
    //BaoLTT
    //admin_dashboard
    @Query("select count(*) from ReportEntity ")
    int countReports();

    @Query("select count(*) from ReportEntity r where r.reportStatus='ĐANG_XỬ_LÝ'")
    int countProcessingReports();

    @Query("select count(*) from ReportEntity r where r.reportStatus='ĐÃ_XỬ_LÝ'")
    int countProcessedReports();


    @Query("SELECT r FROM ReportEntity r WHERE r.houseid = :houseId AND r.userid = :userid ORDER BY r.reportid DESC")
    List<ReportEntity> getReportEntityByUid(int houseId, int userid);

    public ReportEntity save(ReportEntity reportEntity);
    @Transactional
    @Modifying
    @Query("DELETE FROM ReportEntity r WHERE r.houseid = :houseid AND r.userid = :userid")
    void deleteByHouseIdAndMemberId(int houseid, int userid);



    @Query("select new com.roomfindingsystem.dto.ReportListDto(r.reportid,r.reportDescription,r.createdDate,r.reportStatus,r.solvedDate,u.email,h.houseName) from UserEntity u  join " +
            "ReportEntity  r on r.userid= u.userId" +
            " join HousesEntity h on r.houseid = h.houseId")
    List<ReportListDto> findAllReport();

    @Modifying
    @Transactional
    @Query("UPDATE ReportEntity set reportStatus='Chờ Xử Lý' WHERE reportid=?1" )
    int updateStatusReportWaiting(int id);
    @Modifying
    @Transactional
    @Query("UPDATE ReportEntity set reportStatus='Đang Xử Lý' WHERE reportid=?1" )
    int updateStatusReportHandle(int id);
    @Modifying
    @Transactional
    @Query("UPDATE ReportEntity set reportStatus='Đã Xử Lý' WHERE reportid=?1" )
    int updateStatusReportProcessed(int id);

    @Modifying
    @Transactional
    @Query("UPDATE ReportEntity set solvedDate=?1 WHERE reportid=?2")
    int updateProcessedDate(LocalDate dateSolve,int id);



    @Query("select u.email from HousesEntity h join " +
            "UserEntity u on h.userId = u.userId " +
            "WHERE h.houseName = ?1 ")
    Optional<ReportDto> getEmailForRepLy(String houseName);
}
