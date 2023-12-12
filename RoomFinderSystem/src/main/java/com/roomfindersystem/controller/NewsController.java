package com.roomfindersystem.controller;

import com.roomfindersystem.entity.NewsEntity;

import com.roomfindersystem.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/news")
@Controller
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/{id}")
    public String view(Model model, @PathVariable("id") int id, HttpServletRequest request){
        NewsEntity news = newsService.getNewsById(id);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = slider.getCreatedDate().format(formatter);
//        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("news", news);
        model.addAttribute("request",request);
        return "news-detail";
    }


}
