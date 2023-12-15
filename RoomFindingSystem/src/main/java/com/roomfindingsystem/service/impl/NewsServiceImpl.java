package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.NewsEntity;
import com.roomfindingsystem.repository.NewsRepository;

import com.roomfindingsystem.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service("NewsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;




    @Override
    public List<NewsEntity> viewTop7Home() {
        List<NewsEntity> list = new ArrayList<>();
        if(newsRepository.findAll().size()<7){
            return newsRepository.findAll();
        } else {
            for(int i = 0; i<7; i++){
                list.add(newsRepository.findAll().get(i));
            }
            return list;
        }

    }


    public List<NewsEntity> viewAllInAdmin() {
        return newsRepository.findAllInAdmin();
    }

    @Override
    public NewsEntity getNewsById(int newsId) {
        return newsRepository.getNewsEntitiesByNewsid(newsId);
    }

    @Override
    public NewsEntity save(NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    @Override
    public NewsEntity update(NewsEntity newsEntity)  {
        NewsEntity news = newsRepository.findById(newsEntity.getNewsid()).get();
        NewsEntity news1 = new NewsEntity();
        news1.setNewsid(news.getNewsid());
        news1.setTitle(newsEntity.getTitle());
        news1.setContent(newsEntity.getContent());
        news1.setStatus(newsEntity.getStatus());
        news1.setCreatedDate(news.getCreatedDate());
        news1.setLastModifiedDate(LocalDate.now());
        news1.setCreatedBy(news.getCreatedBy());
        news1.setImgLink(newsEntity.getImgLink());
        return newsRepository.save(news1);
    }

    @Override
    public void deleteById(int id) {
         newsRepository.deleteById(id);
    }

    @Override
    public int countNews() {
        return newsRepository.countNews();
    }


}
