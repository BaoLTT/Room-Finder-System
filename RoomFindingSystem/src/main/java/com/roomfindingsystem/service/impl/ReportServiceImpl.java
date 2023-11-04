package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.repository.ReportRepository;
import com.roomfindingsystem.service.ReportService;
import org.springframework.stereotype.Service;

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
}
