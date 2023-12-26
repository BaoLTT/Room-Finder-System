package com.roomfindingsystem.repository;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FavouriteRepositoryTest {
    @Autowired
    private FavouriteRepository favouriteRepository;

    @Test
    void save() {
        FavouriteEntity favourite = new FavouriteEntity();
        favourite.setHouseId(1);
        favourite.setUserId(3);
       FavouriteEntity favouriteEntity = favouriteRepository.save(favourite);

        try {
            // Kiểm tra xem bản ghi đã được lưu thành công hay không
            assertNotNull(favouriteEntity.getFavouriteId());

            // Thêm phần kiểm tra khác nếu cần

        } finally {
            // Sau khi hoàn thành kiểm thử, xóa bản ghi để tránh ảnh hưởng kiểm thử khác
            favouriteRepository.deleteById(favouriteEntity.getFavouriteId());
        }

    }

    @Test
    void findAllFavourite() {
       List<FavouriteDto> list = favouriteRepository.findAllFavourite(2);

       assertNotNull(list);
       assertEquals(1, list.size());
    }

    @Test
    void deleteFavouriteEntitiesByHouseId() {
        favouriteRepository.deleteFavouriteEntitiesByHouseId(6);

        List<FavouriteDto> list = favouriteRepository.findAllFavourite(1);
        assertEquals(1, list.size());
    }

    @Test
    void getAllByHouseId() {
        Optional<FavouriteEntity> optional = favouriteRepository.getAllByHouseId(1, 7);

        assertEquals(111,optional.get().getFavouriteId());
    }

}