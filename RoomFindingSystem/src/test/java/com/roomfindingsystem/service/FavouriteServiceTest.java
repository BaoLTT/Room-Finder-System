package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import com.roomfindingsystem.repository.FavouriteRepository;
import com.roomfindingsystem.service.impl.FavouriteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FavouriteServiceTest {
    @InjectMocks
    FavouriteServiceImpl favouriteService;

    @Mock
    FavouriteRepository favouriteRepository;
    @Test
    void addToFavourite() {
        FavouriteEntity favouriteEntity = new FavouriteEntity();
        favouriteEntity.setUserId(1);

        when(favouriteRepository.save(favouriteEntity)).thenReturn(favouriteEntity);

        FavouriteEntity favourite = favouriteService.addToFavourite(favouriteEntity);

        assertEquals(1, favourite.getUserId());

    }

    @Test
    void getListFavourite() {
        List<FavouriteDto> list1 = new ArrayList<>();
        list1.add(new FavouriteDto());
        when(favouriteRepository.findAllFavourite(1)).thenReturn(list1);

        List<FavouriteDto> list2 = favouriteService.getListFavourite(1);

        assertEquals(1, list2.size());
    }

    @Test
    void removeItemFavourite() {
        // Mocking data
        int houseId = 1;

        // Call the method you want to test
        favouriteService.removeItemFavourite(houseId);

        // Verify that deleteFavouriteEntitiesByHouseId was called with the correct argument
        verify(favouriteRepository).deleteFavouriteEntitiesByHouseId(houseId);
    }

    @Test
    void getAllByHouseId() {
        // Mocking data
        int userId = 1;
        int houseId = 2;

        FavouriteEntity mockFavouriteEntity = new FavouriteEntity();
        // Set properties for mockFavouriteEntity if needed

        // Mocking repository behavior
        when(favouriteRepository.getAllByHouseId(userId, houseId))
                .thenReturn(Optional.of(mockFavouriteEntity));

        // Call the method you want to test
        Optional<FavouriteEntity> result = favouriteService.getAllByHouseId(userId, houseId);

        // Verify that getAllByHouseId was called with the correct arguments
        verify(favouriteRepository).getAllByHouseId(userId, houseId);

        // Verify the result
        assertEquals(mockFavouriteEntity, result.orElse(null));
    }
}