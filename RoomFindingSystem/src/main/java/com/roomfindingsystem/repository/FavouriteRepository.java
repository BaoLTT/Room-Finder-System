package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.FavouriteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity,Integer> {

    FavouriteEntity save(FavouriteEntity favouriteEntity);


    @PersistenceContext
    EntityManager em=null;
    @Query(value = "INSERT INTO `room_finding_system`.`favourite` (`created_date`, `houseid`, `roomid`, `userid`, `status`) VALUES (CURDATE(), ?1, '1', '1', '1');\n", nativeQuery = true)
    FavouriteEntity insert(int id);
}
