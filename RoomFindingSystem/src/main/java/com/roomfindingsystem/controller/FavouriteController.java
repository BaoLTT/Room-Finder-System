package com.roomfindingsystem.controller;

import com.roomfindingsystem.entity.FavouriteEntity;
import com.roomfindingsystem.service.FavouriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;

    @RequestMapping(value = "favourite-list")
    public String getFavourite() {
        return "favourite-list";
    }


    @Transactional
    @RequestMapping(value = "add-favourite-list")
    public String addToFavourite(@RequestParam("id") Integer id, Model model) {
        System.out.println(id);
        favouriteService.insert(id);
//        init(id);
//        FavouriteEntity favouriteEntity = new FavouriteEntity();
//        LocalDate date = LocalDate.now();
//        favouriteEntity.setCreatedDate(date);
//        favouriteEntity.setHouseId(id);
////        favouriteEntity.setRoomId(1);
////        favouriteEntity.setUserId(1);
//        favouriteEntity.setStatus(1);
//        System.out.println(favouriteEntity.getFavouriteId());
//        System.out.println(id);

//        favouriteService.addToFavourite(favouriteEntity);

        return "redirect:/favourite-list";
    }
}
