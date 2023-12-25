package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.AddressEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryTest {
    @Autowired
    AddressRepository addressRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void findByProvinceIdAndDistrictIdAndWardId() {
        int provinceId = 2;
        int districtId = 26;
        int wardId = 718;

        Optional<AddressEntity> entityOptional = addressRepository.findByProvinceIdAndDistrictIdAndWardId(provinceId,districtId,wardId);

        assertTrue(entityOptional.isPresent());
        assertEquals(334, entityOptional.get().getAddressId());


    }


    @Test
    void findByProvinceIdAndDistrictIdAndWardIdAndAddressDetails() {
        int provinceId = 2;
        int districtId = 26;
        int wardId = 718;

        Optional<AddressEntity> entityOptional = addressRepository.findByProvinceIdAndDistrictIdAndWardIdAndAddressDetails(provinceId,districtId,wardId, "123");

        assertTrue(entityOptional.isPresent());
        assertEquals(334, entityOptional.get().getAddressId());

    }

    @Test
    void getAddressById() {
        int adressId = 1;

        AddressEntity addressEntity = addressRepository.getAddressById(adressId);

        assertNotNull(addressEntity);
        assertEquals(40, addressEntity.getProvinceId());

    }

    @Test
    @Transactional
    void updateAddress() {
        addressRepository.updateAddress("Thon 10", 425, 40, 17389, 1);

        AddressEntity addressEntity = addressRepository.getAddressById(1);
        assertNotNull(addressEntity);
        assertEquals("Thon 10", addressEntity.getAddressDetails());
    }
}