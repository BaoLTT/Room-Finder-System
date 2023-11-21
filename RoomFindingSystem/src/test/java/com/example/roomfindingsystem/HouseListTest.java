package com.example.roomfindingsystem;

import com.roomfindingsystem.dto.*;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.repository.HouseRepository;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.service.impl.HouseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HouseListTest {


    HouseService houseService = new HouseService() {

        @Override
        public int countHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service, int countService) {
            return 0;
        }

        @Override
        public List<HouseTypeVo> findHouse(int min1, int max1, int min2, int max2, String houseName, List<Integer> type, List<Integer> service, int countService, int pageIndex, int pageSize) {
            return null;
        }

        @Override
        public List<HouseHomeDto> viewHouseInHome() {
            return null;
        }

        @Override
        public Optional<HousesEntity> findHouseById(Integer id) {
            return Optional.empty();
        }

        @Override
        public List<HouseDto> getHouseDetail(int id) {
            return null;
        }

        @Override
        public List<HouseImageLink> getImageById(int id) {
            return null;
        }

        @Override
        public List<ServiceDto> getServiceById(int id) {
            return null;
        }

        @Override
        public HousesEntity getHouseByRoomId(int roomId) {
            return null;
        }

        @Override
        public int countHousesInAdmin() {
            return 0;
        }

        @Override
        public void updateStar(double star, int houseId) {

        }
    };
    @Test
    public void test1() {
        int min1=2000000;
        int min2=4000000;
        int max1=4000000;
        int max2=6000000;
        String houseName= "";
        List<Integer> type = List.of(1, 2, 3, 4);
        List<Integer> service = List.of(1, 2, 3);
        int countService = 3;
        int count = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);
        assertEquals(1, count);



        // Additional assertions depending on the expected content of the result list
    }
    @Test
    public void test2() {
        int min1=0;
        int min2=0;
        int max1=10000000;
        int max2=10000000;
        String houseName= "";
        List<Integer> type = List.of(1, 2, 3);
        List<Integer> service = null;
        int countService = 0;
        int count = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);
        assertEquals(0, 0);



        // Additional assertions depending on the expected content of the result list
    }
    @Test
    public void test3() {
        int min1=0;
        int min2=0;
        int max1=10000000;
        int max2=10000000;
        String houseName= "Tony";
        List<Integer> type = List.of(0);
        List<Integer> service = List.of(0);
        int countService = 0;
        int count = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);
        assertEquals(1, count);



        // Additional assertions depending on the expected content of the result list
    }
    @Test
    public void test4() {
        int min1=0;
        int min2=0;
        int max1=0;
        int max2=0;
        String houseName= "Tony";
        List<Integer> type = List.of(1, 2, 3);
        List<Integer> service = List.of(1, 2, 3);
        int countService = 3;
        int count = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);
        assertEquals(0, count);



        // Additional assertions depending on the expected content of the result list
    }
    @Test
    public void test5() {
        int min1=0;
        int min2=0;
        int max1=0;
        int max2=0;
        String houseName= "Tony";
        List<Integer> type = List.of(1, 2, 3);
        List<Integer> service = List.of(1, 2, 3);
        int countService = 3;
        int count = houseService.countHouse(min1, max1, min2, max2, houseName, type, service, countService);
        assertEquals(0, count);



        // Additional assertions depending on the expected content of the result list
    }
    @Test
    public void test6() {
        int min1=0;
        int min2=0;
        int max1=10000000;
        int max2=10000000;
        String houseName= "";
        List<Integer> type = List.of(0);
        List<Integer> service = List.of(0);
        int countService = 0;
        int pageIndex = 5;
        int pageSize = 2;
        List<HouseTypeVo> houslist = houseService.findHouse(min1, max1, min2, max2, houseName, type, service, countService, pageIndex, pageSize);
        assertNull(houslist);



        // Additional assertions depending on the expected content of the result list
    }

}
