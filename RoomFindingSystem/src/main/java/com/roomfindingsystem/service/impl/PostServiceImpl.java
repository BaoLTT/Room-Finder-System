package com.roomfindingsystem.service.impl;

import ch.qos.logback.core.joran.sanity.Pair;
import com.roomfindingsystem.entity.PostEntity;
import com.roomfindingsystem.repository.PostRepository;
import com.roomfindingsystem.service.PostService;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

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
        List<PostEntity> allPosts = postRepository.findAllByOrderByCreatedDateAsc();

        Map<String, Long> postCountsByMonth = new LinkedHashMap<>();

        allPosts.stream()
                .collect(Collectors.groupingBy(
                        post -> YearMonth.from(post.getCreatedDate()).toString(),
                        Collectors.counting()
                ))
                .forEach((month, count) -> postCountsByMonth.put(month, count));

        return postCountsByMonth;
    }

}
