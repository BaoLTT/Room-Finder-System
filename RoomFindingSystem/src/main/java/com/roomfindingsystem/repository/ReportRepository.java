package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.ReportListDto;
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

    @Query("select count(*) from ReportEntity r where r.reportStatus='ĐANG_XỬ_LÝ'")
    int countProcessingReports();

    @Query("select count(*) from ReportEntity r where r.reportStatus='ĐÃ_XỬ_LÝ'")
    int countProcessedReports();

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

}
