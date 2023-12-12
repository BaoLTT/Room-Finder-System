package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.PostEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PostService {
    int countPosts();



    Page<PostEntity> getAllPost();
}
