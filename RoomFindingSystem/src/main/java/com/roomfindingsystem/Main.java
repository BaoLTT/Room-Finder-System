package com.roomfindingsystem;

import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.repository.FeedbackRepository;
import com.roomfindingsystem.repository.HouseRepository;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.valves.rewrite.RewriteValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    HouseRepository houseRepository;


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        System.out.println(houseRepository.findHouseByMemberId(3));

    }

}
