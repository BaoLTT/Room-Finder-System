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
    void insertAddressValid() {
        AddressEntity addressEntity = new AddressEntity();
        when(addressRepository.save(addressEntity)).thenReturn(addressEntity);

        addressService.insertAddress(addressEntity);

        // Assert
        verify(addressRepository, times(1)).save(addressEntity);

    }

    @Test
    void insertAddressInvalid() {
        AddressEntity addressEntity = new AddressEntity();

        // Mô phỏng việc lưu địa chỉ thất bại
        when(addressRepository.save(addressEntity)).thenThrow(new RuntimeException("Lỗi khi lưu địa chỉ"));

        // Thực hiện hàm insertAddress
        try {
            addressService.insertAddress(addressEntity);
        } catch (Exception e) {
            assertEquals("Lỗi khi lưu địa chỉ", e.getMessage());
        }

        // Assert
        verify(addressRepository, times(1)).save(addressEntity);
    }

    @Test
    void updateAddressValid() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressDetails("New Address Details");
        addressEntity.setDistrictId(1);
        addressEntity.setProvinceId(2);
        addressEntity.setWardId(3);
        int id = 1;

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
    void updateAddressUnsuccessful() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressDetails("New Address Details");
        addressEntity.setDistrictId(0);
        addressEntity.setProvinceId(2);
        addressEntity.setWardId(3);
        int id = 1;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }

    @Test
    void updateAddressUnsuccessful2() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressDetails("New Address Details");
        addressEntity.setDistrictId(0);
        addressEntity.setProvinceId(2);
        addressEntity.setWardId(3);
        int id = 2;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }

    @Test
    void updateAddressValidZero() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressDetails("New Address Details");
        addressEntity.setDistrictId(1);
        addressEntity.setProvinceId(2);
        addressEntity.setWardId(3);
        int id = 0;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }

    @Test
    void updateAddressInValidZero() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressDetails("New Address Details");
        addressEntity.setDistrictId(0);
        addressEntity.setProvinceId(2);
        addressEntity.setWardId(3);
        int id = 0;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }

    @Test
    void updateAddressNullZero() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        int id = 0;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }
    @Test
    void updateAddressNulland2() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        int id = 2;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }

    @Test
    void updateAddressNull() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        int id = 1;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }

    @Test
    void updateAddressValid2() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressDetails("New Address Details");
        addressEntity.setDistrictId(1);
        addressEntity.setProvinceId(2);
        addressEntity.setWardId(3);
        int id = 2;

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
    void updateAddressAdressNull() {
        // Mocking data
        AddressEntity addressEntity = new AddressEntity();
        int id = 42;

        // Mock the repository behavior when updateAddress is called
        doNothing().when(addressRepository).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );

        // Call the method you want to test
        addressService.updateAddress(addressEntity, id);

        // Verify that updateAddress method was called with the correct arguments
        verify(addressRepository, times(1)).updateAddress(
                addressEntity.getAddressDetails(),
                addressEntity.getDistrictId(),
                addressEntity.getProvinceId(),
                addressEntity.getWardId(),
                id
        );
    }
    @Test
    void findbyId() {
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

    @Test
    void findbyId2() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(2);
        int id = 2;

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

    @Test
    void findbyId3() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(0);
        int id = 0;

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

    @Test
    void findbyId4() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(-1);
        int id = -1;

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