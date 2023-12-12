package com.roomfindersystem.service.impl;

import com.roomfindersystem.entity.AddressEntity;
import com.roomfindersystem.repository.AddressRepository;
import com.roomfindersystem.service.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Override
    public int insertAddress(AddressEntity address) {
        addressRepository.save(address);
        return address.getAddressId();
    }

    @Transactional
    @Override
    public void updateAddress(AddressEntity address, int id) {
        addressRepository.updateAddress(address.getAddressDetails(), address.getDistrictId(), address.getName(), address.getProvinceId(),address.getWardId(),id);
    }

    @Override
    public Optional<AddressEntity> findbyId(Integer addresID) {
        return addressRepository.findById(addresID);
    }
}
