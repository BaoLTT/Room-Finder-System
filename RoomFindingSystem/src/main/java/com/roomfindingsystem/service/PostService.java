package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.PostEntity;

import java.util.List;
import java.util.Map;

public interface PostService {
    int countPosts();

    Map<String, Long> countPostByMonth();
}
