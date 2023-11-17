package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.ReportEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reportRepository")
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
    //BaoLTT
    //admin_dashboard
    @Query("select count(*) from ReportEntity ")
    int countReports();

    @Query("select count(*) from ReportEntity r where r.reportStatusid=1")
    int countProcessingReports();

    @Query("select count(*) from ReportEntity r where r.reportStatusid=2")
    int countProcessedReports();

    @Query("SELECT r FROM ReportEntity r WHERE r.houseid = :houseId AND r.userid = :userid")
    List<ReportEntity> getReportEntityByUid(int houseId, int userid);
    public ReportEntity save(ReportEntity reportEntity);
    @Transactional
    @Modifying
    @Query("DELETE FROM ReportEntity r WHERE r.houseid = :houseid AND r.userid = :userid")
    void deleteByHouseIdAndMemberId(int houseid, int userid);


}
