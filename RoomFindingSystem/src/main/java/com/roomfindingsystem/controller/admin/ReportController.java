package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.service.EmailSenderService;
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
    private final EmailSenderService emailSenderService;

    public ReportController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @RequestMapping(value = "list-report-admin")
    public String getListReport(Model model) {
        try {
            List<ReportListDto> listReport = reportService.getAllReport();
            model.addAttribute("reportList", listReport);

            System.out.println(listReport.toString());

            return "report-list-admin";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }


    }

    @RequestMapping(value = "waiting")
    public String updateWaiting(Model model, @Param("id") int id) {
        try {
            reportService.updateStatusWaiting(id);
            List<ReportListDto> listReport = reportService.getAllReport();
            model.addAttribute("reportList", listReport);
            return "report-list-admin";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }


    }

    @RequestMapping(value = "handle")
    public String updateHandle(Model model, @Param("id") int id) {
        try {
            reportService.updateStatusHandle(id);
            List<ReportListDto> listReport = reportService.getAllReport();
            String subject = "Chào Chủ Nhà Trọ " + listReport.get(id).getHouseName();
//        String email = String.valueOf(reportService.getEmailForReply(listReport.get(id).getHouseName()));
//        System.out.println(listReport.get(id).getHouseName());
//        System.out.println("emaibjdfnvkdnfk"+email);
//        String mess = "Chúng Tôi Vừa Nhận Được Phản Hồi Với Nội Dung "+" '"+ listReport.get(id).getReportDescription()+" ' ,"+"\n Chúng Tôi Sẽ đến Xác Minh lại Vấn Đề Trên Với Nhà Trọ Của Bạn. Mong Bạn Sẽ Liên Hệ Lại với Chúng Tôi Qua Số HOTLINE: 0888848962";
//        this.emailSenderService.sendEmail(email, subject, mess);
            model.addAttribute("reportList", listReport);

            return "report-list-admin";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";

        }

    }

    @RequestMapping(value = "processed")
    public String updateProcessed(Model model, @Param("id") int id) {
        try {
            reportService.updateStatusProcessed(id);
            List<ReportListDto> listReport = reportService.getAllReport();
            String subject = "Chào Bạn, Cảm ơn bạn đã dành thời gian gửi báo cáo nhà trọ : " + listReport.get(id).getHouseName();
//        String mess = "Chúng Tôi Đã Nhận Được Phản Hồi Với Nội Dung "+" '"+ listReport.get(id).getReportDescription()+" ' ,"+"\n Chúng Tôi Đã đến Xác Minh lại Vấn Đề Trên Với Chủ Nhà Trọ . Mọi Thắc Mắc Mong Bạn Sẽ Liên Hệ Lại với Chúng Tôi Qua Số HOTLINE: 0888848962";
//        this.emailSenderService.sendEmail(listReport.get(id).getEmail(), subject, mess);
            System.out.println("sdnvjdfemaibjdfnvkdnfk" + listReport.get(id).getEmail());
            model.addAttribute("reportList", listReport);


            return "report-list-admin";

        } catch (Exception exception) {
            exception.printStackTrace();
            return "404";
        }

    }
}
