package com.roomfindingsystem.controller.admin;

import com.roomfindingsystem.service.PostService;
import com.roomfindingsystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/admin/data")
public class AdminDashBoardRestController {
    @Autowired
    PostService postService;

    @Autowired
    ReportService reportService;




    @GetMapping("/pie")
    public Map<String, Object> getPieChartData() {
        Map<String, Object> data = new HashMap<>();
        data.put("labels", Arrays.asList("Đang xử lý", "Đã xử lý"));
        List<Integer> dataValues = Arrays.asList(reportService.countProcessingReports(), reportService.countProcessedReports());

        Map<String, Object> dataset = new HashMap<>();
        dataset.put("data", dataValues);
        dataset.put("backgroundColor", Arrays.asList("#5d6dc3", "#3ec396"));
        dataset.put("hoverBackgroundColor", Arrays.asList("#5d6dc3", "#3ec396"));
        dataset.put("hoverBorderColor", "#fff");

        data.put("datasets", Arrays.asList(dataset));
        return data;
    }

}
