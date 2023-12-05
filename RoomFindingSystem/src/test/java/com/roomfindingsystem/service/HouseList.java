//package com.example.roomfindingsystem.service;
//
//import com.roomfindingsystem.repository.HouseRepository;
//import com.roomfindingsystem.service.impl.HouseServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.Bean;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//
//public class HouseList{
//    @Bean
//    public  HouseServiceImpl houseService;
//
//    @Test
//    public void testCountHouse() {
//        // Arrange
//        int min1 = 2000000;
//        int max1 = 4000000;
//        int min2 = 4000000;
//        int max2 = 6000000;
//        String houseName = "";
//        List<Integer> type = Arrays.asList(1, 2, 3);
//        List<Integer> service = Arrays.asList(4, 5, 6);
//        int countService = 3;
//
//        // Mocking the behavior of houseRepository.countHouse
//
//
//
//        int result = houseRepository.countHouse(min1, max1, min2, max2, houseName, type, service, countService);
//
//
//        // Asserting the result
//        System.out.println(result);
//    }
//}
