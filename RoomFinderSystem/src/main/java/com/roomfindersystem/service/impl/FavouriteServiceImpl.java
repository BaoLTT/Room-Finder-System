package com.roomfindersystem.service.impl;

import com.roomfindersystem.dto.FavouriteDto;
import com.roomfindersystem.entity.FavouriteEntity;
import com.roomfindersystem.repository.FavouriteRepository;
import com.roomfindersystem.service.FavouriteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    FavouriteRepository favouriteRepository;

    @Override

    public FavouriteEntity addToFavourite(FavouriteEntity favouriteEntity) {
        return favouriteRepository.save(favouriteEntity);
    }

    @Override
    public List<FavouriteDto> getListFavourite(int id) {
        return favouriteRepository.findAllFavourite(id);
    }

    @Override
    public void removeItemFavourite(int houseId) {

        favouriteRepository.deleteFavouriteEntitiesByHouseId(houseId);
    }

    @Override
    public Optional<FavouriteEntity> getAllByHouseId(int userid,int houseid) {
        return favouriteRepository.getAllByHouseId(userid,houseid);
    }


}
