package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface FavouriteService {


    FavouriteEntity addToFavourite(FavouriteEntity favouriteEntity);
    List<FavouriteDto> getListFavourite();
    void removeItemFavourite(int houseId);
    Optional<FavouriteEntity> getAllByHouseId(int houseid);

}
