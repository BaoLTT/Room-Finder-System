package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public interface FavouriteService {


    FavouriteEntity addToFavourite(FavouriteEntity favouriteEntity);
    FavouriteEntity insert(int id);
}
