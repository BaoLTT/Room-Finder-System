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

    @GetMapping("/bar")
    public Map<String, Object> getPostData() {
        Map<String, Object> data = new HashMap<>();

        // Sử dụng service để lấy dữ liệu thống kê theo tháng
        Map<String, Long> postCountsByMonth = postService.countPostByMonth();

        List<String> labels = new ArrayList<>();
        List<Long> dataValues = new ArrayList<>();

        // Sắp xếp thống kê theo tháng
        postCountsByMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    labels.add(entry.getKey());
                    dataValues.add(entry.getValue());
                });

        Map<String, Object> dataset = new HashMap<>();
        dataset.put("data", dataValues);
        dataset.put("backgroundColor", "rgba(60, 134, 216, 0.3)");
        dataset.put("hoverBackgroundColor", "rgba(60, 134, 216, 0.7)");
        dataset.put("hoverBorderColor", "#3c86d8");
        dataset.put("borderWidth", 2);
        dataset.put("borderColor", "#3c86d8");
        dataset.put("label", "Số bài đăng");

        data.put("labels", labels);
        data.put("datasets", Arrays.asList(dataset));

        return data;
    }


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
