package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.entity.ReportEntity;

import java.util.List;

public interface ReportService {
    int countReports();

    int countProcessingReports();

    int countProcessedReports();

    List<ReportListDto> getAllReport();

    int updateStatusProcessed(int id);
    int updateStatusHandle(int id);
    int updateStatusWaiting(int id);
}
