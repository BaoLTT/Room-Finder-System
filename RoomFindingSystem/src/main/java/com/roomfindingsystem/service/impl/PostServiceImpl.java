package com.roomfindingsystem.service.impl;


import com.roomfindingsystem.entity.PostEntity;
import com.roomfindingsystem.repository.PostRepository;
import com.roomfindingsystem.service.PostService;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        super();
        this.postRepository = postRepository;
    }
    @Override
    public int countPosts() {
        return postRepository.countPosts();
    }

    @Override
    public Map<String, Long> countPostByMonth() {
        // Lấy ngày hiện tại
        YearMonth currentYearMonth = YearMonth.now();

        // Tạo một danh sách các YearMonth cho 6 tháng gần đây (tính từ tháng hiện tại)
        List<YearMonth> last6Months = Stream.iterate(currentYearMonth, ym -> ym.minusMonths(1))
                .limit(6)
                .collect(Collectors.toList());

        // Lấy danh sách bài viết trong cơ sở dữ liệu
        List<PostEntity> allPosts = postRepository.findAllByOrderByCreatedDateAsc();

        // Tạo một map để lưu trữ tổng số bài viết cho mỗi tháng
        Map<String, Long> postCountsByMonth = new HashMap<>();

        // Điền giá trị mặc định (0) cho tất cả các tháng
        last6Months.forEach(ym -> postCountsByMonth.put(ym.toString(), 0L));

        // Tính toán tổng số bài viết cho từng tháng
        allPosts.stream()
                .filter(post -> {
                    YearMonth postYearMonth = YearMonth.from(post.getCreatedDate());
                    return postYearMonth.isAfter(currentYearMonth.minusMonths(5)); // Chỉ xem xét 6 tháng gần đây
                })
                .collect(Collectors.groupingBy(
                        post -> YearMonth.from(post.getCreatedDate()).toString(),
                        Collectors.counting()
                ))
                .forEach((month, count) -> postCountsByMonth.put(month, count));

        return postCountsByMonth;
    }

}
