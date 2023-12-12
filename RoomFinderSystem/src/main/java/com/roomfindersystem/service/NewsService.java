package com.roomfindersystem.service;


import com.roomfindersystem.entity.NewsEntity;

import java.util.List;

public interface NewsService {
    List<NewsEntity> viewTop7Home();


    List<NewsEntity> viewAllInAdmin();

    NewsEntity getNewsById(int newsId);

    NewsEntity save(NewsEntity newsEntity) ;

    NewsEntity update(NewsEntity newsEntity);

    void deleteById(int id);

    int countNews();


}
