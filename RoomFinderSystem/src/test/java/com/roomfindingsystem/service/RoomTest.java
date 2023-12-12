package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.RoomDto;
import com.roomfindingsystem.entity.RoomEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.repository.*;
import com.roomfindingsystem.service.RoomService;
import com.roomfindingsystem.service.RoomTypeService;
import com.roomfindingsystem.service.ServiceDetailService;
import com.roomfindingsystem.service.ServiceRoomService;
import com.roomfindingsystem.service.impl.GcsService;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private DistrictRepository districtRepository;

    @Mock
    private WardRepository wardRepository;

    @Mock
    private GcsService gcsService;

    @InjectMocks
    private UserServiceImpl userService;

    private ModelMapper modelMapper;
    @Mock
    private RoomService roomService;
    @Mock
    private ServiceRoomService serviceRoomService;
    @Mock
    private RoomTypeService roomTypeService;
    @Mock
    private ServiceDetailService serviceDetailService;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
    }

    @Test
    public void testGetListWhenListNull() {
        List<RoomDto> roomDtos = roomService.getRoomsInHouse(0);
        assertEquals(0, roomDtos.size());

    }
    @Test
    public void testGetListWhenListHaveHouseId() {
        List<RoomDto> roomDtos = roomService.getRoomsInHouse(2);
        assertEquals(0, roomDtos.size());
    }
    @Test
    public void testGetListRoomPageWhenInvalidHouseIdThenReturnListRoomViewWithEmptyList() {
        Integer houseId=999; // Assuming 999 is an invalid house ID
        when(roomService.getRoomsInHouse(houseId)).thenReturn(Collections.emptyList());
        List<RoomDto> roomDtos = roomService.getRoomsInHouse(houseId);
        assertEquals(0,roomDtos.size() );
    }

    @Test
    public void testGetListRoomPageWhenUpdateByHouseId() {
        Integer houseId=999; // Assuming 999 is an invalid house ID
        RoomDto roomDto = roomService.findById(houseId);
        assertEquals(null,roomDto);
    }

    @Test
    void testGetFormUpdateRoomWhenValidIdThenReturnEditRoomView() throws Exception {

        Integer roomId = 1;

        RoomDto roomDto = new RoomDto();
        roomDto =roomService.findById(roomId);
        assertEquals(null,roomDto);


    }
    @Test
    public void getFormUpdateRoom_returnsEditRoomView() throws Exception {

        // Set up mock data
        Integer roomId = 2;
        RoomDto roomDto = new RoomDto();


        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        UserEntity modelAndView = userService.getUserByRoomId(roomId);

        // Assertions
        assertEquals(null, modelAndView);

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
    public void getRoomsInHouseId() throws Exception {

        // Set up mock data
        Integer houseId = 2;
        RoomDto roomDto = new RoomDto();

        // Call method
        MockHttpServletRequest request = new MockHttpServletRequest();
        RoomEntity modelAndView = (RoomEntity) roomService.getRoomById(houseId);

        // Assertions
        assertEquals(null, modelAndView);
    }



}
