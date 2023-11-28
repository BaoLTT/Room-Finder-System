package com.roomfindingsystem.service;

import java.util.Map;

public interface PostService {
    int countPosts();

    Map<String, Long> countPostByMonth();
}
