package com.example.roomfindingsystem;

import com.roomfindingsystem.repository.PostRepository;
import com.roomfindingsystem.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    PostRepository postRepository;
    @InjectMocks
    PostServiceImpl postService;
    @Test
    void whenCountPosts_shouldReturnInt(){
        //1. Create mock
        int mockPosts = 0;

        //2. define behavior of Repository
        when(postRepository.countPosts()).thenReturn(mockPosts);

        // 3. call service method
        int actualPosts = postService.countPosts();

        // 4. assert the result
        assertThat(actualPosts).isEqualTo(mockPosts);

        // 4.1 ensure repository is called
        verify(postRepository).countPosts();

    }
}
