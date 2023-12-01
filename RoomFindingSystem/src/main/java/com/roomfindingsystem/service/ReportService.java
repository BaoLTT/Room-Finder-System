package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.ReportDto;
import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.entity.ReportEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    int countReports();

    int countProcessingReports();

    int countProcessedReports();

    ReportEntity save(ReportEntity reportEntity);

    List<ReportEntity> getReportEntityByUid(int houseid, int userid);

    void deleteByHouseIdAndMemberId(int houseid, int userid);


    List<ReportListDto> getAllReport();

    int updateStatusProcessed(int id);
    int updateStatusHandle(int id);
    int updateStatusWaiting(int id);

    int updateSolve(LocalDate solveDate, int id);

    Optional<ReportDto> getEmailForReply(String houseName);
}
