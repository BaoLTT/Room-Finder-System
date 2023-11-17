package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.FavouriteDto;
import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.FavouriteEntity;
import com.roomfindingsystem.service.FavouriteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;

    @RequestMapping(value = "favourite-list")
    public String getFavourite() {
        return "favourite-list";
    }

    @RequestMapping(value = "add-favourite-list")
    public String addToFavourite(@Param("id") Integer id, ModelMap model) {
        FavouriteDto favouriteDto = new FavouriteDto();
        LocalDate currentDateTime = LocalDate.now();
        favouriteDto.setCreatedDate(currentDateTime);
        favouriteDto.setHouseId(id);
        favouriteDto.setUserId(1);
        favouriteDto.setStatus(true);
//        favouriteService.addToFavourite(favouriteDto);
        return "favourite-list";
    }
}
