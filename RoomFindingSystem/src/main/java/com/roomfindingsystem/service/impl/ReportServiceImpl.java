package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.ReportListDto;
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
    public List<ReportListDto> getAllReport() {
        return reportRepository.findAllReport();
    }

    @Override
    public int updateStatusProcessed(int id) {
        return reportRepository.updateStatusReportProcessed(id);
    }

    @Override
    public int updateStatusHandle(int id) {
        return reportRepository.updateStatusReportHandle(id);
    }

    @Override
    public int updateStatusWaiting(int id) {
        return reportRepository.updateStatusReportWaiting(id);
    }
}
