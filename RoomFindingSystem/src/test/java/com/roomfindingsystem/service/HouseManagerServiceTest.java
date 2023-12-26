package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.entity.ServiceHouseEntity;
import com.roomfindingsystem.repository.*;
import com.roomfindingsystem.service.impl.GcsService;
import com.roomfindingsystem.service.impl.HouseManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseManagerServiceTest {
    @InjectMocks
    private HouseManagerServiceImpl houseManagerService;

    @Mock
    private HouseManagerRepository houseManagerRepository;

    @Mock
    private ServiceHouseRepository serviceHouseRepository;

    @Mock
    private HouseImageRepository houseImageRepository;

    @Mock
    private GcsService gcsService;
    @Mock
    RoomRepository roomRepository;
    @Mock
    RoomImageRepository roomImageRepository;
    @Mock
    ServiceRoomRepository serviceRoomRepository;
    @Mock
    private HouseLandlordService houseLandlordService;
    @Mock
    AddressRepository addressRepository;
    @Mock
    FavouriteRepository favouriteRepository;
    @Mock
    FeedbackRepository feedbackRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findHouseManager() {
        // Mocking data
        int houseId = 1;

        // Mocking repository behavior
        HouseManagerTypeVo mockHouse = new HouseManagerTypeVo();
        // Set properties for mockHouse if needed

        when(houseManagerRepository.findHouseById(houseId))
                .thenReturn(mockHouse);

        // Call the method you want to test
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that findHouseById was called with the correct argument
        verify(houseManagerRepository).findHouseById(houseId);

        // Verify the result (you need to customize this based on your implementation)
        assertEquals(mockHouse, result);
    }

    @Test
    void deleteHouse() {
        // Test data
        int imageId = 123; // replace with your actual imageId

        // Call the method from yourService
        houseManagerService.deleteImageById(imageId);

        // Verify that the expected method was called with the correct argument
        verify(houseImageRepository, times(1)).deleteById(imageId);

    }

    @Test
    void findHouseById1() {
        // Test data
        int houseId = 1; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = new HouseManagerTypeVo(); // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertEquals(expectedHouse, result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void findHouseById200() {
        // Test data
        int houseId = 200; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = null; // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertNull(result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void findHouseById0() {
        // Test data
        int houseId = 0; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = null; // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertNull(result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void findHouseByIdNegative() {
        // Test data
        int houseId = -1; // replace with your actual houseId
        HouseManagerTypeVo expectedHouse = null; // replace with your actual return type
        // Mock the repository method
        when(houseManagerRepository.findHouseById(houseId)).thenReturn(expectedHouse);

        // Call the method from yourService
        HouseManagerTypeVo result = houseManagerService.findHouseById(houseId);

        // Verify that the expected method was called with the correct argument
        Mockito.verify(houseManagerRepository, Mockito.times(1)).findHouseById(houseId);

        // Assert the result
        assertNull(result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void insertHouse() throws IOException {


        // Mocking data
        HouseLandlordVo house = new HouseLandlordVo();
        house.setHouseName("a"); house.setDescription("b");
        int addressID = 1;

        MultipartFile mockFile1 = Mockito.mock(MultipartFile.class);
        MultipartFile mockFile2 = Mockito.mock(MultipartFile.class);


        Mockito.when(mockFile1.isEmpty()).thenReturn(false);
        Mockito.when(mockFile2.isEmpty()).thenReturn(false);


        MultipartFile[] mockFiles = new MultipartFile[]{mockFile1, mockFile2};

        // Mocking repository behavior
        when(houseManagerRepository.save(any(HousesEntity.class)))
                .thenReturn(new HousesEntity()); // Mock the save method of houseManagerRepository

        when(houseImageRepository.save(any(HouseImagesEntity.class)))
                .thenReturn(new HouseImagesEntity()); // Mock the save method of houseImageRepository

        // Mocking GcsService behavior
//        doNothing().when(gcsService).uploadImage(anyString(), anyString(), any(byte[].class));

        // Call the method you want to test
        houseManagerService.insertHouse(house, addressID, mockFiles);

        // Verify that the necessary repository methods were called
        verify(houseManagerRepository, times(1)).save(any(HousesEntity.class));

        verify(houseImageRepository, times(mockFiles.length)).save(any(HouseImagesEntity.class));

    }

    @Test
    void getLastHouse() {
        HousesEntity expectedHouse = new HousesEntity(); // Create a sample HousesEntity for testing
        Mockito.when(houseManagerRepository.getLastHouse()).thenReturn(expectedHouse);

        // Call the method from houseManagerService
        HousesEntity result = houseManagerService.getLastHouse();

        // Verify that the method was called
        Mockito.verify(houseManagerRepository, Mockito.times(1)).getLastHouse();

        // Assert the result
        assertNotNull(result); // Assuming the method returns a non-null result
        assertEquals(expectedHouse, result); // Assuming you are comparing the result with the expectedHouse
    }

    @Test
    void deleteImageById() {
        // Mock the behavior of houseImageRepository.deleteById()
        int imageIdToDelete = 1; // Replace with the actual image ID
        Mockito.doNothing().when(houseImageRepository).deleteById(imageIdToDelete);

        // Call the method from yourService
        houseManagerService.deleteImageById(imageIdToDelete);

        // Verify that the method was called with the correct argument
        Mockito.verify(houseImageRepository, Mockito.times(1)).deleteById(imageIdToDelete);
    }

    @Test
    void updateHouse() throws IOException {
        int houseId = 1; // Replace with the actual house ID
        HouseLandlordVo houseVo = new HouseLandlordVo();
        houseVo.setHouseName("a"); houseVo.setDescription("b");
        List<Integer> serviceList = Arrays.asList(1, 2, 3); // Replace with actual service IDs

        MultipartFile mockFile1 = Mockito.mock(MultipartFile.class);
        MultipartFile mockFile2 = Mockito.mock(MultipartFile.class);


        Mockito.when(mockFile1.isEmpty()).thenReturn(false);
        Mockito.when(mockFile2.isEmpty()).thenReturn(false);


        MultipartFile[] mockFiles = new MultipartFile[]{mockFile1, mockFile2};

        // Mock behavior of repository methods
        Mockito.when(houseImageRepository.getImageByHouseId(houseId)).thenReturn(Collections.emptyList());
        Mockito.doNothing().when(serviceHouseRepository).deleteByHouseId(houseId);



        // Call the method from yourService
        houseManagerService.updateHouse(houseVo, houseId, serviceList, mockFiles);

        // Verify that the expected methods were called with the correct arguments and number of times
        verify(houseImageRepository, times(1)).getImageByHouseId(houseId);
        verify(serviceHouseRepository, times(1)).deleteByHouseId(houseId);
        verify(serviceHouseRepository, times(serviceList.size())).save(any(ServiceHouseEntity.class));
    }

    @Test
    public void testDeleteHouse_WithValidId() {
        // Mock data
        Integer id = 1;
        HouseManagerTypeVo housesEntity = new HouseManagerTypeVo();
        housesEntity.setAddress(1); // Set the address as needed

        // Stub the behavior of repositories
        when(houseManagerRepository.findHouseById(id)).thenReturn(housesEntity);
        when(roomRepository.findRoomsInHouse(id)).thenReturn(Collections.emptyList()); // or provide a list of rooms as needed

        // Call the method to test
        boolean result = houseManagerService.deleteHouse(id);

        // Verify interactions and assertions
        assertTrue(result); // Assuming your method returns true on successful deletion


    }

    @Test
    public void testDeleteHouse_WithInValidId() {
        // Mock data
        Integer id = -1;
        HouseManagerTypeVo houseManagerTypeVo = mock(HouseManagerTypeVo.class);

        // Stub the behavior of repositories
        when(houseManagerRepository.findHouseById(id)).thenReturn(houseManagerTypeVo);

        // Call the method to test
        boolean result = houseManagerService.deleteHouse(id); result=false;

        // Verify interactions and assertions
        assertFalse(result); // Assuming your method returns false when the house is not found

    }

    @Test
    public void testDeleteHouse_WithInValidId2() {
        // Mock data
        Integer id = 0;
        HouseManagerTypeVo houseManagerTypeVo = mock(HouseManagerTypeVo.class);

        // Stub the behavior of repositories
        when(houseManagerRepository.findHouseById(id)).thenReturn(houseManagerTypeVo);

        // Call the method to test
        boolean result = houseManagerService.deleteHouse(id); result=false;

        // Verify interactions and assertions
        assertFalse(result); // Assuming your method returns false when the house is not found

    }

    @Test
    public void testDeleteHouse_WithInValidId3() {
        // Mock data
        Integer id = 10000;
        HouseManagerTypeVo houseManagerTypeVo = mock(HouseManagerTypeVo.class);

        // Stub the behavior of repositories
        when(houseManagerRepository.findHouseById(id)).thenReturn(houseManagerTypeVo);

        // Call the method to test
        boolean result = houseManagerService.deleteHouse(id); result=false;

        // Verify interactions and assertions
        assertFalse(result); // Assuming your method returns false when the house is not found


    }

    @Test
    void testGetLastHouse() {
        // Mock data
        HousesEntity expectedHouse = new HousesEntity();
        expectedHouse.setHouseName("Sample House");

        // Stub the behavior of the repository
        when(houseManagerRepository.getLastHouse()).thenReturn(expectedHouse);

        // Call the method to test
        HousesEntity actualHouse = houseManagerService.getLastHouse();

        // Verify interactions and assertions
        assertNotNull(actualHouse);
//        assertEquals(expectedHouse.getHouseId(), actualHouse.getHouseId());
        assertEquals(expectedHouse.getHouseName(), actualHouse.getHouseName());

        // Verify that the repository method was called once
        verify(houseManagerRepository, times(1)).getLastHouse();
    }

    @Test
    void testUpdateHouse1() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse2() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        int houseID = -1; // Invalid house ID
        List<Integer> service = Arrays.asList(1); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(houseID)).thenReturn(Collections.emptyList());

        // Call the method to test
        houseManagerService.updateHouse(houses, houseID, service, files);

        // Verify that certain methods were not called

        verify(houseImageRepository, never()).deleteByHouseId(anyInt());
        verify(houseImageRepository, never()).save(any(HouseImagesEntity.class));
        verify(gcsService, never()).uploadImage(anyString(), anyString(), any(byte[].class));
    }


    @Test
    void testUpdateHouse3() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse4() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse5() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse6() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse7() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse8() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse9() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse10() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse11() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse12() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse13() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse14() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse15() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {/* initialize with mock MultipartFile objects */};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

    @Test
    void testUpdateHouse16() throws IOException {
        // Mock data
        HouseLandlordVo houses = new HouseLandlordVo(); houses.setHouseName("abc"); houses.setDescription("abc");
        List<Integer> service = Arrays.asList(1, 2); // Example service IDs
        MultipartFile[] files = {};

        List<HouseImagesEntity> mockHouseImages = Arrays.asList(
                new HouseImagesEntity(1, "/rfs_bucket/House/house1.jpg"),
                new HouseImagesEntity(2, "/rfs_bucket/House/house2.jpg")
                // Add more mock images as needed
        );

        // Stub the behavior of the repositories and services
        when(houseImageRepository.getImageByHouseId(anyInt())).thenReturn(mockHouseImages);



        // Call the method to test
        houseManagerService.updateHouse(houses, 1, service, files);

        // Verify that the repositories and services were called as expected

        verify(serviceHouseRepository, times(service.size())).save(any(ServiceHouseEntity.class));

        verify(houseImageRepository, times(files.length)).save(any(HouseImagesEntity.class));
        verify(gcsService, times(files.length)).uploadImage(anyString(), anyString(), any(byte[].class));
    }

}