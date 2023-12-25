package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.NewsEntity;
import com.roomfindingsystem.repository.NewsRepository;
import com.roomfindingsystem.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {
    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private NewsRepository newsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void viewTop7Home() {
        // Test data
        List<NewsEntity> allNews = new ArrayList<>();
        allNews.add(new NewsEntity());
        // Populate allNews with data, adjust as needed
        when(newsRepository.findAll()).thenReturn(allNews);

        // Call the method from yourService
        List<NewsEntity> result = newsService.viewTop7Home();



        // Assert the result
        assertEquals(allNews, result);
    }

    @Test
    void viewAllInAdmin() {
        // Test data
        List<NewsEntity> expectedNewsList = new ArrayList<>();

        // Mock the behavior of newsRepository.findAllInAdmin()
        when(newsRepository.findAllInAdmin()).thenReturn(expectedNewsList);

        // Call the method from yourService
        List<NewsEntity> result = newsService.viewAllInAdmin();



        // Assert the result
        assertEquals(expectedNewsList, result);
    }

    @Test
    void getNewsById() {
        // Test data
        int newsId = 1;
        NewsEntity expectedNewsEntity = new NewsEntity();
        // Populate expectedNewsEntity with data, adjust as needed

        // Mock the behavior of newsRepository.getNewsEntitiesByNewsid(newsId)
        when(newsRepository.getNewsEntitiesByNewsid(newsId)).thenReturn(expectedNewsEntity);

        // Call the method from yourService
        NewsEntity result = newsService.getNewsById(newsId);


        assertEquals(expectedNewsEntity, result);
    }

    @Test
    void save() {

        // Test data
        NewsEntity inputNewsEntity = new NewsEntity();
        // Populate inputNewsEntity with data, adjust as needed
        NewsEntity expectedSavedNewsEntity = new NewsEntity();


        // Mock the behavior of newsRepository.save(newsEntity)
        when(newsRepository.save(inputNewsEntity)).thenReturn(expectedSavedNewsEntity);

        // Call the method from yourService
        NewsEntity result = newsService.save(inputNewsEntity);

        // Verify that save was called once with the correct argument
        verify(newsRepository).save(inputNewsEntity);

        // Assert the result
        assertEquals(expectedSavedNewsEntity, result);
    }

    @Test
    void update() {

        // Test data
        NewsEntity inputNewsEntity = new NewsEntity();
        // Populate inputNewsEntity with data, adjust as needed
        NewsEntity expectedSavedNewsEntity = new NewsEntity();
        // Populate expectedSavedNewsEntity with data, adjust as needed

        // Mock the behavior of newsRepository.save(newsEntity)
        when(newsRepository.save(inputNewsEntity)).thenReturn(expectedSavedNewsEntity);

        // Call the method from yourService
        NewsEntity result = newsService.save(inputNewsEntity);

        // Verify that save was called once with the correct argument
        verify(newsRepository).save(inputNewsEntity);

        // Assert the result
        assertEquals(expectedSavedNewsEntity, result);
    }

    @Test
    void deleteById() {
        // Test data
        int newsIdToDelete = 1; // Assuming this ID exists in your repository



        // Call the method from yourService
        newsService.deleteById(newsIdToDelete);

        // Verify that deleteById was called once with the correct argument
        verify(newsRepository).deleteById(newsIdToDelete);
    }

    @Test
    void countNews() {
        // Test data
        int expectedCount = 5; // Modify this according to your expectations

        // Mock the behavior of newsRepository.countNews()
        when(newsRepository.countNews()).thenReturn(expectedCount);

        // Call the method from yourService
        int result = newsService.countNews();

        // Verify that countNews was called once
        verify(newsRepository).countNews();

        // Assert the result
        assertEquals(expectedCount, result); // Assuming you are comparing the result with the expected count
    }
}