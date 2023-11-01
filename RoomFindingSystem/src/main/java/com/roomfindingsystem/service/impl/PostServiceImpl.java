package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.repository.PostRepository;
import com.roomfindingsystem.service.PostService;
import org.springframework.stereotype.Service;

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
}
