package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.repository.AddressRepository;
import com.roomfindingsystem.service.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
