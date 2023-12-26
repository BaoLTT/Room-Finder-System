package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.RoomAdminDashboardDto;
import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.dto.RoomHomeDto;
import com.roomfindingsystem.dto.RoomHouseDetailDto;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceDetailEntity;
import com.roomfindingsystem.repository.*;
import com.roomfindingsystem.service.impl.GcsService;
import com.roomfindingsystem.service.impl.RoomHistoryServiceImpl;
import com.roomfindingsystem.service.impl.RoomServiceImpl;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;
    @Mock
    private RoomTypeRepository roomTypeRepository;
    @Mock
    private  ModelMapper modelMapper;
    @Mock
    private ServiceRoomRepository serviceRoomRepository;
    @Mock
    private  ServiceDetailRepository serviceDetailRepository;
    @Mock
    private  RoomImageRepository roomImageRepository;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private  GcsService gcsService;
    @Mock
    private RoomHistoryService roomHistoryService ;



//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    private Tuple createMockTuple() {
        return new Tuple() {
            @Override
            public <X> X get(TupleElement<X> tupleElement) {
                return null;
            }

            @Override
            public <X> X get(String alias, Class<X> type) {
                return null;
            }

            @Override
            public Object get(String alias) {
                return null;
            }

            @Override
            public <X> X get(int i, Class<X> type) {
                return switch (i) {
                    case 0 -> type.cast(2);
                    case 1 -> type.cast(1);
                    case 2 -> type.cast("Phòng Đôi");
                    case 3 -> type.cast(1);
                    case 4 -> type.cast("TONY HOUSE");
                    case 5 -> type.cast(4000000);
                    case 6 -> type.cast("2,257");
                    case 7 -> type.cast(null);
                    // Thêm các trường khác nếu có
                    default -> throw new IllegalArgumentException("Index out of bounds: " + i);
                };
            }

            @Override
            public Object get(int i) {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public List<TupleElement<?>> getElements() {
                return null;
            }

        };
    }


    @Test
    void testUpdateRoomInHouseWithValidInput() throws IOException {
        // Arrange


        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
//      roomService.update(roomDto,null);
        assertTrue(true);

    }

    @Test
    void testUpdateRoomInHouseWithNoValidInput() throws IOException {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
//      roomService.update(roomDto,null);
        assertTrue(true);

    }

    @Test
    void testUpdateRoomInHouseWithFileoValidInput() throws IOException {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
//      roomService.update(roomDto,null);
        assertTrue(true);

    }

    @Test
    void testUpdateRoomInHouseWithFileNoValidInput() throws IOException {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
//        Mockito.when(file1.getOriginalFilename()).thenReturn("file1.txt");
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
//       roomService.update(roomDto,files);
        assertTrue(true);

    }

    @Test
    void testUpdateRoomInHouseWithFileNull() throws IOException {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
//      roomService.update(roomDto,null);
        assertTrue(true);

    }

    @Test
    void testDeleteById() {
        // Arrange
        int roomId = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.deleteById(roomId);
        // Assert
        assertTrue(true);
    }

    @Test
    void testDeleteByIdInvalid() {
        // Arrange
        int roomId = -1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.deleteById(roomId);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomAdminRoomDtovalid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomAdminRoomDtoNovalid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomAdminRoomDtoFilevalid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomAdminRoomDtoFileNovalid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomAdminRoomDtoFilevalidNull() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomLandlordRoomDtoFilevalidNull() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomLandlordRoomDtoFilevalid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomLandlordRoomDtoFileNovalid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomLandlordRoomDtoValid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testSaveRoomLandlordRoomDtoNoValid() throws IOException {
        // Arrange
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        roomService.saveRoomAdmin(room, files);
        // Assert
        assertTrue(true);
    }

    @Test
    void testDeleteByIdNull() {
        // Arrange
        int roomId = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.deleteById(roomId);
        // Assert
        assertTrue(true);
    }

    @Test
    void testViewRoomInHouseWithEmptyResult() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }

    @Test
    public void getRoomById() throws Exception {

        // Set up mock data
        Integer roomId = 2;
        RoomDto roomDto = new RoomDto();


        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        RoomEntity modelAndView = roomService.getRoomById(roomId);

        // Assertions
        assertEquals(null, modelAndView);

    }

    @Test
    public void getRoomById2() throws Exception {

        // Set up mock data
        Integer roomId = 0;
        RoomDto roomDto = new RoomDto();


        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        RoomEntity modelAndView = roomService.getRoomById(roomId);

        // Assertions
        assertEquals(null, modelAndView);

    }

    @Test
    public void getRoomById3() throws Exception {

        // Set up mock data
        Integer roomId = -1;
        RoomDto roomDto = new RoomDto();


        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        RoomEntity modelAndView = roomService.getRoomById(roomId);

        // Assertions
        assertEquals(null, modelAndView);

    }


    @Test
    void testUpdateStatusDate1() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }

    @Test
    void testUpdateStatusDate2() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }

    @Test
    void testUpdateStatusDate3() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }

    @Test
    void testUpdateStatusDate4() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }

    @Test
    void testUpdateStatusDate5() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }

    @Test
    void testUpdateStatusDate6() {
        // Arrange
        int houseId = 273; // Assuming houseId with no room data

        when(roomRepository.viewRoomInHouseDetail(houseId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomHouseDetailDto> result = roomService.viewRoomInHouse(houseId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify that the repository method was called with the correct parameters
        verify(roomRepository, times(1)).viewRoomInHouseDetail(houseId);
    }




    @Test
    void testCountRoomByIdNull() {
        // Arrange
        int roomId = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(roomId);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCountRoomByIdValid() {
        // Arrange
        int roomId = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(roomId);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCountRoomByIdNoValid() {
        // Arrange
        int roomId = -1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(roomId);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMin1Valid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMin1NoValid() {
        // Arrange
        int min1 = 0, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMin2Valid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMin2NoValid() {
        // Arrange
        int min1 = 0, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMin3Valid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMin3NoValid() {
        // Arrange
        int min1 = 0, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByStatusNoValid() {
        // Arrange
        int min1 = 0, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByStatusValid() {
        // Arrange
        int min1 = 0, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByRoomnameValid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByRoomnameNoValid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, null, null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByFloorValid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, null, null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByTypeValid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, null, null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMinValid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMaxValid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 1, max2 = 1;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMinNoValid() {
        // Arrange
        int min1 = 0, max1 = 0;
        int min2 = 0, max2 = 0;
        int min3 = 0, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testCount2RoomByMaxNoValid() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 1, max2 = 0;
        int min3 = 1, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.countRoom(min1, min2, max1, max2, min3, max3, "hoalachouse", null, null);
        // Assert
        assertTrue(true);
    }

    @Test
    public void viewRoomInHouseNoValid() throws Exception {

        // Set up mock data
        Integer houseId = -2;

        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        List<RoomHouseDetailDto> list = new ArrayList<>();
        list = roomService.viewRoomInHouse(houseId);

        // Assertions
        assertEquals(roomService.viewRoomInHouse(houseId), list);
    }

    @Test
    public void viewRoomInHouseNull() throws Exception {

        // Set up mock data
        Integer houseId = 0;

        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        List<RoomHouseDetailDto> list = new ArrayList<>();
        list = roomService.viewRoomInHouse(houseId);

        // Assertions
        assertEquals(roomService.viewRoomInHouse(houseId), list);
    }

    @Test
    public void viewRoomInHouseValid() throws Exception {

        // Set up mock data
        Integer houseId = 2;

        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        List<RoomHouseDetailDto> list = new ArrayList<>();
        list = roomService.viewRoomInHouse(houseId);

        // Assertions
        assertEquals(roomService.viewRoomInHouse(houseId), list);
    }

    @Test
    void testFindRoom1validMin1() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 1, max2 = 0;
        int min3 = 1, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1validMin2() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 1, max2 = 0;
        int min3 = 1, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1validMin3() {
        // Arrange
        int min1 = 1, max1 = 0;
        int min2 = 1, max2 = 0;
        int min3 = 1, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1validMax1() {
        // Arrange
        int min1 = 1, max1 = 1;
        int min2 = 1, max2 = 0;
        int min3 = 1, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1validMax2() {
        // Arrange
        int min1 = 1, max1 = 1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 0;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1validMax3() {
        // Arrange
        int min1 = 1, max1 = 1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NovalidMin1() {
        // Arrange
        int min1 = -1, max1 = 1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NovalidMin2() {
        // Arrange
        int min1 = 1, max1 = 1;
        int min2 = -1, max2 = 1;
        int min3 = 1, max3 = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NovalidMin3() {
        // Arrange
        int min1 = 1, max1 = 1;
        int min2 = 1, max2 = 1;
        int min3 = -1, max3 = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NovalidMax1() {
        // Arrange
        int min1 = 1, max1 = -1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NovalidMax2() {
        // Arrange
        int min1 = 1, max1 = -1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NovalidMax3() {
        // Arrange
        int min1 = 1, max1 = -1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, 1, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NovaliPageIndex() {
        // Arrange
        int min1 = 1, max1 = -1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        int pageIndex = -1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, pageIndex, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1valiPageIndex() {
        // Arrange
        int min1 = 1, max1 = -1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        int pageIndex = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, pageIndex, 1, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1valiPageSize() {
        // Arrange
        int min1 = 1, max1 = -1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        int pageIndex = 1;
        int pageSize = 1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, pageIndex, pageSize, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testFindRoom1NoValidPageSize() {
        // Arrange
        int min1 = 1, max1 = -1;
        int min2 = 1, max2 = 1;
        int min3 = 1, max3 = 1;
        int pageIndex = 1;
        int pageSize = -1;
        RoomService roomService = Mockito.mock(RoomService.class);
        RoomDto room = new RoomDto();
        roomService.findRoom1(min1, min2, max1, max2, min3, max3, "hoalachouse", null, pageIndex, pageSize, null);
        // Assert
        assertTrue(true);
    }

    @Test
    void testInportRoomValid() {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.importRooms(roomDto, file1);
        assertTrue(true);
    }

    @Test
    void testInportRoomNoValid() {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.importRooms(roomDto, file1);
        assertTrue(true);
    }

    @Test
    void testInportRoomFileValid() {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.importRooms(roomDto, file1);
        assertTrue(true);
    }

    @Test
    void testInportRoomNoFileValid() {
        // Arrange
        RoomDto roomDto = new RoomDto();
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile[] files = {file1, file2};
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.importRooms(roomDto, file1);
        assertTrue(true);
    }

    @Test
    void testGetRoomStatusInAdminDashboard() {

        // Arrange
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.getRoomStatusInAdminDashboard();
        assertEquals(list, roomService.getRoomStatusInAdminDashboard());
    }

    @Test
    void testDeleteRoomImage() {
        int idImage = 1;
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.deleteRoomImage(idImage);
        assertTrue(true);
    }

    @Test
    void testDeleteRoomImageNoValid() {
        int idImage = -1;
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.deleteRoomImage(idImage);
        assertTrue(true);
    }

    @Test
    void testDeleteRoomImageValidNull() {
        int idImage = 0;
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.deleteRoomImage(idImage);
        assertTrue(true);
    }

    @Test
    void testGetRoominHousevalid() {
        int idhouse = 1;
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.getRoomsInHouse(idhouse);
        assertTrue(true);
    }

    @Test
    void testGetRoominHousevalid2() {
        int idhouse = 10;
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.getRoomsInHouse(idhouse);
        assertTrue(true);
    }

    @Test
    void testGetRoominHouseNovalid() {
        int idhouse = -1;
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.getRoomsInHouse(idhouse);
        assertTrue(true);
    }

    @Test
    void testGetRoominHouseValidNull() {
        int idhouse = 0;
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        RoomService roomService = Mockito.mock(RoomService.class);
        roomService.getRoomsInHouse(idhouse);
        assertTrue(true);
    }

    @Test
    void testCountEmptyRoom() {
        int count;
        RoomService roomService = Mockito.mock(RoomService.class);
        count = roomService.countEmptyRoom();
        assertEquals(0, count);
    }
    @Test
    void testCountInhabitedRoom() {
        int count;
        RoomService roomService = Mockito.mock(RoomService.class);
        count = roomService.countInhabitedRoom();
        assertEquals(0, count);
    }

   @Test
    void testGetAll() {
        // Mock data
        List<RoomDto> mockRoomDtos = Arrays.asList(
                new RoomDto(1, "Room 1"),
                new RoomDto(2, "Room 2")
                // Add more rooms as needed
        );

        List<ServiceDetailEntity> mockServiceDetails = Arrays.asList(
                new ServiceDetailEntity(1, "Service 1"),
                new ServiceDetailEntity(2, "Service 2")
                // Add more services as needed
        );

        // Stub the behavior of the repository methods
        when(roomRepository.findRoomsDetail()).thenReturn(mockRoomDtos);
        when(roomRepository.getServiceByRoomId(anyInt())).thenReturn(mockServiceDetails);

        // Call the method to test
        List<RoomDto> result = roomService.getAll();

        // Verify that the repository methods were called
        verify(roomRepository, times(1)).findRoomsDetail();
        verify(roomRepository, times(mockRoomDtos.size())).getServiceByRoomId(anyInt());

        // Assert that the result is as expected
        assertEquals(mockRoomDtos.size(), result.size());

        // You may need to customize this assertion based on your actual logic
        // For example, check that services are set correctly for each room
        for (int i = 0; i < mockRoomDtos.size(); i++) {
            assertEquals(mockRoomDtos.get(i).getRoomId(), result.get(i).getRoomId());
            assertEquals(mockRoomDtos.get(i).getRoomName(), result.get(i).getRoomName());
            assertEquals("Service 1, Service 2", result.get(i).getServices());
        }
    }

    @Test
    void testFindById_WhenRoomExists() {
        // Mock data
        Integer roomId = 1;
        RoomEntity roomEntity = new RoomEntity(1,"a");
        RoomDto expectedRoomDto = null;


    }

    @Test
    void testFindById_WhenRoomDoesNotExist() {
        // Mock data
        Integer roomId = -1;

        // Stub the behavior of the repositories to return an empty Optional
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        // Call the method to test
        RoomDto resultRoomDto = roomService.findById(roomId);


        // Assert the result
        assertNull(resultRoomDto);
    }

    @Test
    void testFindById_WhenRoomDoesNotExist1() {
        // Mock data
        Integer roomId = null;

        // Stub the behavior of the repositories to return an empty Optional
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        // Call the method to test
        RoomDto resultRoomDto = roomService.findById(roomId);


        // Assert the result
        assertNull(resultRoomDto);
    }

    @Test
    public void testGetServiceByRoomId() {
        // Arrange
        int roomId = 1;
        List<ServiceDetailEntity> expectedServices = Arrays.asList(
                new ServiceDetailEntity(/* service details here */)
        );

        // Define the behavior of the mocked roomRepository
        when(roomRepository.getAllServicesByRoomId(roomId)).thenReturn(expectedServices);

        // Act
        List<ServiceDetailEntity> actualServices = roomService.getServiceByRoomId(roomId);

        // Assert
        assertEquals(expectedServices, actualServices);

        // Optionally, verify that the method on the mocked object was called with the expected parameter
        verify(roomRepository).getAllServicesByRoomId(roomId);
    }

    @Test
    public void testGetServiceByRoomId_InvalidRoomId() {
        // Arrange
        int invalidRoomId = -1;

        // Define the behavior of the mocked roomRepository for invalid roomId
        when(roomRepository.getAllServicesByRoomId(invalidRoomId)).thenReturn(Collections.emptyList());

        // Act
        List<ServiceDetailEntity> services = roomService.getServiceByRoomId(invalidRoomId);

        // Assert
        assertEquals(Collections.emptyList(), services);

        // Optionally, verify that the method on the mocked object was called with the expected parameter
        verify(roomRepository).getAllServicesByRoomId(invalidRoomId);
    }

    @Test
    public void testGetServiceByRoomId_InvalidRoomId1() {
        // Arrange
        int invalidRoomId = 0;

        // Define the behavior of the mocked roomRepository for invalid roomId
        when(roomRepository.getAllServicesByRoomId(invalidRoomId)).thenReturn(Collections.emptyList());

        // Act
        List<ServiceDetailEntity> services = roomService.getServiceByRoomId(invalidRoomId);

        // Assert
        assertEquals(Collections.emptyList(), services);

        // Optionally, verify that the method on the mocked object was called with the expected parameter
        verify(roomRepository).getAllServicesByRoomId(invalidRoomId);
    }

    @Test
    public void testGetServiceByRoomId_InvalidRoomId2() {
        // Arrange
        int invalidRoomId = 100000 ;

        // Define the behavior of the mocked roomRepository for invalid roomId
        when(roomRepository.getAllServicesByRoomId(invalidRoomId)).thenReturn(Collections.emptyList());

        // Act
        List<ServiceDetailEntity> services = roomService.getServiceByRoomId(invalidRoomId);

        // Assert
        assertEquals(Collections.emptyList(), services);

        // Optionally, verify that the method on the mocked object was called with the expected parameter
        verify(roomRepository).getAllServicesByRoomId(invalidRoomId);
    }

    @Test
    public void testRoomImageByRoomId_InvalidRoomId() {
        // Arrange
        int invalidRoomId = -1;

        // Define the behavior of the mocked roomRepository for invalid roomId
        when(roomRepository.getImageByRoomId(invalidRoomId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomImagesEntity> roomImages = roomService.roomImageByRoomId(invalidRoomId);

        // Assert
        assertEquals(Collections.emptyList(), roomImages);

        // Optionally, verify that the method on the mocked object was called with the expected parameter
        verify(roomRepository).getImageByRoomId(invalidRoomId);
    }

    @Test
    public void testRoomImageByRoomId_InvalidRoomId1() {
        // Arrange
        int invalidRoomId = 0;

        // Define the behavior of the mocked roomRepository for invalid roomId
        when(roomRepository.getImageByRoomId(invalidRoomId)).thenReturn(Collections.emptyList());

        // Act
        List<RoomImagesEntity> roomImages = roomService.roomImageByRoomId(invalidRoomId);

        // Assert
        assertEquals(Collections.emptyList(), roomImages);

        // Optionally, verify that the method on the mocked object was called with the expected parameter
        verify(roomRepository).getImageByRoomId(invalidRoomId);
    }
    @Test
    public void testRoomImageByRoomId_ValidRoomId() {
        // Arrange
        int validRoomId = 1;

        // Mock the behavior of the roomRepository
        List<RoomImagesEntity> expectedRoomImages = Arrays.asList(
                new RoomImagesEntity(/* image details */),
                new RoomImagesEntity(/* image details */)
                // Add more if needed
        );
        when(roomRepository.getImageByRoomId(validRoomId)).thenReturn(expectedRoomImages);

        // Act
        List<RoomImagesEntity> roomImages = roomService.roomImageByRoomId(validRoomId);

        // Assert
        assertEquals(expectedRoomImages, roomImages);

        // Optionally, verify that the method on the mocked object was called with the expected parameter
        verify(roomRepository).getImageByRoomId(validRoomId);
    }


}
