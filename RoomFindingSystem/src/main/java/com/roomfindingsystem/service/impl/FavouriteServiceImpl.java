package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import com.roomfindingsystem.repository.FavouriteRepository;
import com.roomfindingsystem.service.FavouriteService;
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
    public List<FavouriteDto> getListFavourite() {
        return favouriteRepository.findAllFavourite();
    }

    @Override
    public void removeItemFavourite(int houseId) {

        favouriteRepository.deleteFavouriteEntitiesByHouseId(houseId);
    }

    @Override
    public Optional<FavouriteEntity> getAllByHouseId(int houseid) {
        return favouriteRepository.getAllByHouseId(houseid);
    }


}
