package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.AddressEntity;
import com.roomfindingsystem.repository.AddressRepository;
import com.roomfindingsystem.repository.UserRepository;
import com.roomfindingsystem.service.impl.AddressServiceImpl;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    AddressServiceImpl addressService;
    @Test
    void insertAddress() {
        AddressEntity addressEntity = new AddressEntity();
        when(addressRepository.save(addressEntity)).thenReturn(addressEntity);

        addressService.insertAddress(addressEntity);

        // Assert
        verify(addressRepository, times(1)).save(addressEntity);

    }

    @Test
    void updateAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressDetails("New Address Details");
        addressEntity.setDistrictId(1);
        addressEntity.setProvinceId(2);
        addressEntity.setWardId(3);
        int id = 42;

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that the updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );}

    @Test
    void findbyId() {


        // Create an instance of YourService and inject the mock repository


        // Create an example AddressEntity and ID
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(1);
        int id = 1;

        // Set up the mock behavior
        when(addressRepository.findById(id)).thenReturn(Optional.of(addressEntity));

        // Call the method you want to test
        Optional<AddressEntity> result = addressService.findbyId(id);

        // Verify that the findById method was called with the correct argument
        verify(addressRepository, times(1)).findById(id);

        // Verify that the result is as expected
        assertTrue(result.isPresent());
        assertEquals(addressEntity, result.get());
    }
}