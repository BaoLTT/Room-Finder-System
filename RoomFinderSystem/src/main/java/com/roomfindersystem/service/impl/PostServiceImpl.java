package com.roomfindersystem.service.impl;


import com.roomfindersystem.entity.PostEntity;
import com.roomfindersystem.repository.PostRepository;
import com.roomfindersystem.service.PostService;
import org.springframework.data.domain.Page;
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
    public Page<PostEntity> getAllPost() {
        return null;
    }

}
