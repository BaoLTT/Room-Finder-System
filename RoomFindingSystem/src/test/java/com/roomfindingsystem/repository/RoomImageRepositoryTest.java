package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.RoomImagesEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomImageRepositoryTest {
    @Autowired
    private RoomImageRepository repository;
    @Test
    void getImageByRoomId() {
       List<RoomImagesEntity> list = repository.getImageByRoomId(2);

       assertNotNull(list);
       assertEquals(1,list.size());
    }

    @Test
    @Transactional
    void deleteByImageId() {
        repository.deleteByImageId(1);

        List<RoomImagesEntity> list = repository.getImageByRoomId(2);
        assertEquals(1,list.size());
    }
}