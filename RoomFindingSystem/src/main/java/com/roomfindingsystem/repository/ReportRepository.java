package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("reportRepository")
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
    //BaoLTT
    //admin_dashboard
    @Query("select count(*) from ReportEntity ")
    int countReports();
}
