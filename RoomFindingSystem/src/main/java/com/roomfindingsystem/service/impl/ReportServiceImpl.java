package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.repository.ReportRepository;
import com.roomfindingsystem.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository){
        super();
        this.reportRepository = reportRepository;
    }
    @Override
    public int countReports() {
        return reportRepository.countReports();
    }

    @Override
    public int countProcessingReports() {
        return reportRepository.countProcessingReports();
    }

    @Override
    public int countProcessedReports() {
        return reportRepository.countProcessedReports();
    }

    @Override
    public ReportEntity save(ReportEntity reportEntity) {
        return reportRepository.save(reportEntity);
    }

    @Override
    public List<ReportEntity> getReportEntityByUid(int houseid, int userid) {
        return reportRepository.getReportEntityByUid(houseid, userid);
    }

    @Override
    public void deleteByHouseIdAndMemberId(int houseid, int userid) {
        reportRepository.deleteByHouseIdAndMemberId(houseid, userid);
    }
}
