package com.roomfindersystem.service;

import com.roomfindersystem.dto.FavouriteDto;
import com.roomfindersystem.entity.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface FavouriteService {


    FavouriteEntity addToFavourite(FavouriteEntity favouriteEntity);
    List<FavouriteDto> getListFavourite( int id);
    void removeItemFavourite(int houseId);
    Optional<FavouriteEntity> getAllByHouseId(int userid , int houseid);

}
