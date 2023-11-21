package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ReportController {
    @Autowired
    ReportService reportService;
    @RequestMapping(value = "list-report-admin")
    public String getListReport(Model model) {
        List<ReportListDto> listReport= reportService.getAllReport();
        model.addAttribute("reportList", listReport);
        System.out.println(listReport.toString());
        return "report-list-admin";
    }

    @RequestMapping(value = "waiting")
    public String updateWaiting(Model model, @Param("id") int id) {
        reportService.updateStatusWaiting(id);
        List<ReportListDto> listReport= reportService.getAllReport();
        model.addAttribute("reportList", listReport);

        return "report-list-admin";
    }
    @RequestMapping(value = "handle")
    public String updateHandle(Model model , @Param("id") int id) {
        reportService.updateStatusHandle(id);
        List<ReportListDto> listReport= reportService.getAllReport();
        model.addAttribute("reportList", listReport);

        return "report-list-admin";
    }
    @RequestMapping(value = "processed")
    public String updateProcessed(Model model , @Param("id") int id) {
        reportService.updateStatusProcessed(id);
        List<ReportListDto> listReport= reportService.getAllReport();
        model.addAttribute("reportList", listReport);

        return "report-list-admin";
    }
}
