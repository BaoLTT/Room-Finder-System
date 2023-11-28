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
        System.out.println(list);
        return "favourite-list";
    }

    @RequestMapping(value = "remove-favourite-list")
    public String removeFavourite(@RequestParam("id") Integer id) {
        favouriteService.removeItemFavourite(id);
        return "redirect:/favourite-list";
    }



    @RequestMapping(value = "add-favourite-list")
            FavouriteEntity favouriteEntity = new FavouriteEntity();
            LocalDate now = LocalDate.now();
            // get session id
            favouriteEntity.setCreatedDate(now);
            favouriteEntity.setHouseId(id);
            favouriteService.addToFavourite(favouriteEntity);
        }else{
            System.out.println("false");
        }


        return "redirect:/houselist";
    }


}
