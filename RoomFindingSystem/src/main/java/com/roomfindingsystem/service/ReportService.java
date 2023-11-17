package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.ReportEntity;

import java.util.List;

public interface ReportService {
    int countReports();

    int countProcessingReports();

    int countProcessedReports();
    ReportEntity save(ReportEntity reportEntity);

    List<ReportEntity> getReportEntityByUid(int houseid, int userid);

    void deleteByHouseIdAndMemberId(int houseid, int userid);
}
