package com.roomfindingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "favourite", schema = "room_finding_system", catalog = "")
public class FavouriteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "FavouriteID")
    private int favouriteId;


    @Basic
    @Column(name = "HouseID")
    private int houseId;


    @Basic
    @Column(name = "RoomID")
    private int roomId;


    @Basic
    @Column(name = "UserID")
    private int userId;


    @Basic
    @Column(name = "Created_Date")
    private LocalDate createdDate;
    @Basic
    @Column(name = "status")
    private int status;




}
