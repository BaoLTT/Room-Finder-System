package com.roomfindingsystem.controller;

import com.roomfindingsystem.repository.ServiceDetailRepositoryTest;
import com.roomfindingsystem.service.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HouseListControllerTest {
    @InjectMocks
    private HouseListController houseListController;

    @Mock
    private HouseService houseService;

    @Mock
    private ServiceDetailRepositoryTest serviceDetailRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testList() {
//        // Mocking the dependencies
//        Model model = Mockito.mock(Model.class);
//        MockHttpSession httpSession = new MockHttpSession();
//        List<HouseTypeVo> mockHouseList = Arrays.asList(/* Populate with mock data */);
//        int mockTotalHouse = 10;
//
//        when(houseService.findHouse(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyList(), anyList(), anyInt(), anyInt(), anyInt()))
//                .thenReturn(mockHouseList);
//        when(houseService.countHouse(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyList(), anyList(), anyInt()))
//                .thenReturn(mockTotalHouse);
//
//        // Invoke the controller method
//        String result = houseListController.list(1, "houseName", Arrays.asList("1", "2"), Arrays.asList("1", "2"), Arrays.asList("1", "2"), model, httpSession);
//
//        // Assert the result
//        assertEquals("houselist", result);
//
//        // Assert the model attributes
//        assertEquals("houseName", model.getAttribute("houseName"));
//        assertEquals(Arrays.asList("1", "2"), model.getAttribute("listPrice"));
//        assertEquals(Arrays.asList("1", "2"), model.getAttribute("listType"));
//        assertEquals(Arrays.asList("1", "2"), model.getAttribute("listService"));
//        assertEquals(1, model.getAttribute("currentPage"));
//        assertEquals(2, model.getAttribute("totalPage"));
//        assertEquals(mockHouseList, model.getAttribute("houses"));
//        assertEquals(/* Add expected value for listAllService */, model.getAttribute("listAllService"));
//    }
}
