package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.AddressEntity;

import java.util.Optional;

public interface AddressService {
    int insertAddress(AddressEntity address);

    void updateAddress(AddressEntity address,int id);

    Optional<AddressEntity> findbyId(Integer addresID);
}
