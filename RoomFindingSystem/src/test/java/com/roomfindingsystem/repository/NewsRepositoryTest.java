package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.NewsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NewsRepositoryTest {
    @Autowired
    private NewsRepository newsRepository;
    @Test
    void findAll() {
      List<NewsEntity> list =  newsRepository.findAll();

      assertNotNull(list);
      assertEquals(4, list.size());
    }

    @Test
    void findAllInAdmin() {
        List<NewsEntity> list = newsRepository.findAllInAdmin();

        assertNotNull(list);
        assertEquals(4, list.size());
    }

    @Test
    void getNewsEntitiesByNewsid() {
       NewsEntity newsEntity =  newsRepository.getNewsEntitiesByNewsid(2);

        assertNotNull(newsEntity);
        assertEquals(4, newsEntity.getCreatedBy());
    }

    @Test
    void countNews() {
       int count = newsRepository.countNews();

        assertEquals(4, count);
    }
}