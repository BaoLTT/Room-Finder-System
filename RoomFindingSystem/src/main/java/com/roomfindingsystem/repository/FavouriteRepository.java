package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.entity.FavouriteEntity;
import com.roomfindingsystem.entity.FeedbackEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends CrudRepository<FavouriteEntity, Integer>,JpaRepository<FavouriteEntity,Integer> {

//    Optional<FavouriteDto> save();
}
