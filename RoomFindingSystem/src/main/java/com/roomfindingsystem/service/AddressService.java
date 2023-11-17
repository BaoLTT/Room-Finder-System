package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.AddressEntity;

public interface AddressService {
    int insertAddress(AddressEntity address);

    void updateAddress(AddressEntity address,int id);
}
