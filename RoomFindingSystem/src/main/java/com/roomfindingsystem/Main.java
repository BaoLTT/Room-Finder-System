package com.roomfindingsystem;

import com.roomfindingsystem.controller.MainController;
import com.roomfindingsystem.reponsitory.FeedbackRepository;
import com.roomfindingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main  {
// implements CommandLineRunner
    @Autowired
    private FeedbackRepository feedbackRepository;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(feedbackRepository.findFeedbackDtosByHouseId(2).get(0).getTitle());
//    }
}
