package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import com.roomfindingsystem.repository.FavouriteRepository;
import com.roomfindingsystem.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    FavouriteRepository favouriteRepository;


//    @Override
//    public void addToFavourite(FavouriteDto favouriteDto) {
//        favouriteRepository.save();
//    }
}
