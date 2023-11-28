package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.FavouriteDto;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;


    @RequestMapping(value = "favourite-list")
    public String getFavourite(Model model) {
        List<FavouriteDto> list = favouriteService.getListFavourite();
        System.out.println(list);
        model.addAttribute("listFavourite",list);
        return "favourite-list";
    }

    @RequestMapping(value = "remove-favourite-list")
    public String removeFavourite(@RequestParam("id") Integer id) {
        favouriteService.removeItemFavourite(id);
        return "redirect:/favourite-list";
    }



    @RequestMapping(value = "add-favourite-list")
    public String addToFavourite(@RequestParam("id") Integer id, Model model) {
        if (!favouriteService.getAllByHouseId(id).isPresent()){
            FavouriteEntity favouriteEntity = new FavouriteEntity();
            LocalDate now = LocalDate.now();
            // get session id
            favouriteEntity.setUserId(1);
            favouriteEntity.setRoomId(1);
            favouriteEntity.setCreatedDate(now);
            favouriteEntity.setHouseId(id);
            favouriteService.addToFavourite(favouriteEntity);
            model.addAttribute("favouriteEntity", favouriteService.getAllByHouseId(id));
        }else{
            System.out.println("false");
        }


        return "redirect:/houselist";
    }


}
