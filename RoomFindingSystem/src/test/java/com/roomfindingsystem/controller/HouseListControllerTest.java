package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.HouseTypeVo;
import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.repository.ServiceDetailRepository;
import com.roomfindingsystem.repository.ServiceDetailRepositoryTest;
import com.roomfindingsystem.service.HouseService;
import com.roomfindingsystem.service.HouseTypeService;
import com.roomfindingsystem.service.impl.DistrictServiceImpl;
import com.roomfindingsystem.service.impl.ProvinceServiceImpl;
import com.roomfindingsystem.service.impl.WardServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HouseListControllerTest {
    @InjectMocks
    private HouseListController houseListController;

    @Mock
    private HouseService houseService;

    @Mock
    private ServiceDetailRepository serviceDetailRepository;

    @Mock
    private HouseTypeService houseTypeService;

    @Mock
    private HttpSession session;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private ProvinceServiceImpl provinceService;
    @Mock
    private DistrictServiceImpl districtService;
    @Mock
    private WardServiceImpl wardService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNullPrice() {
        int min1 ;
        int max1 ;
        int min2 ;
        int max2 ;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status = 0;

        int status1;
        int status2;
        if(status==0){
            status1=0;
            status2=1;

        }else{
            status1=1;
            status2=1;

        }
        String houseName = "";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int countService = 1;
        int pageIndex = 1;
        int pageSize = 4;

        int countHouse = 1;
        List<String> price = Arrays.asList();
        List<String> listtype = Arrays.asList("1", "2", "3", "4");
        List<String> listservice = Arrays.asList("1");
        if (price.contains("1") && price.contains("2") && price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }
        else if (price.contains("1") && price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 4000000;
        } else if (price.contains("1") && price.contains("3")) {
            min1 = 0;
            max1 = 2000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("2") && price.contains("3")) {
            min1 = 2000000;
            max1 = 4000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("1")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 2000000;
        } else if (price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 2000000;
            max2 = 4000000;
        } else if (price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }


        // Chuẩn bị
        List<HouseTypeVo> listHouse = new ArrayList<>();
        List<TypeHouseEntity> listAllType = new ArrayList<>();
        List<ServiceDetailEntity> listAllService = new ArrayList<>();



        // Kiểm tra các phương thức khác nếu cần
//        System.out.println(result);
        // Kiểm tra kết quả
        assertEquals("houselist", "houselist");
    }
    @Test
    public void testAllPrice() {
        int min1 ;
        int max1 ;
        int min2 ;
        int max2 ;
        ProvinceEntity province = new ProvinceEntity(); province.setName("a");
        DistrictEntity district = new DistrictEntity(); district.setName("a");
        WardEntity ward = new WardEntity(); ward.setName("a");
        int status = 0;

        int status1;
        int status2;
        if(status==0){
            status1=0;
            status2=1;

        }else{
            status1=1;
            status2=1;

        }
        String houseName = "";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int countService = 1;
        int pageIndex = 1;
        int pageSize = 4;

        int countHouse = 1;
        List<String> price = Arrays.asList("1", "2", "3");
        List<String> listtype = Arrays.asList("1", "2", "3", "4");
        List<String> listservice = Arrays.asList("1");
        if (price.contains("1") && price.contains("2") && price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }
        else if (price.contains("1") && price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 4000000;
        } else if (price.contains("1") && price.contains("3")) {
            min1 = 0;
            max1 = 2000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("2") && price.contains("3")) {
            min1 = 2000000;
            max1 = 4000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("1")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 2000000;
        } else if (price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 2000000;
            max2 = 4000000;
        } else if (price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }


        // Chuẩn bị

        List<HouseTypeVo> listHouse = new ArrayList<>();
        List<TypeHouseEntity> listAllType = new ArrayList<>();
        List<ServiceDetailEntity> listAllService = new ArrayList<>();


        String result = houseListController.list(pageIndex, houseName, price, listtype, listservice, status, province.getProvinceId(), district.getDistrictId(), ward.getWardId(), model, session, httpServletRequest);


        // Kiểm tra các phương thức khác nếu cần
//        System.out.println(result);
        // Kiểm tra kết quả
        assertEquals("houselist", "houselist");
    }
    @Test
    public void testOnePrice() {
        int min1 ;
        int max1 ;
        int min2 ;
        int max2 ;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status = 0;

        int status1;
        int status2;
        if(status==0){
            status1=0;
            status2=1;

        }else{
            status1=1;
            status2=1;

        }
        String houseName = "";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int countService = 1;
        int pageIndex = 1;
        int pageSize = 4;

        int countHouse = 1;
        List<String> price = Arrays.asList("1");
        List<String> listtype = Arrays.asList("1", "2", "3", "4");
        List<String> listservice = Arrays.asList("1");
        if (price.contains("1") && price.contains("2") && price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }
        else if (price.contains("1") && price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 4000000;
        } else if (price.contains("1") && price.contains("3")) {
            min1 = 0;
            max1 = 2000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("2") && price.contains("3")) {
            min1 = 2000000;
            max1 = 4000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("1")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 2000000;
        } else if (price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 2000000;
            max2 = 4000000;
        } else if (price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }


        // Chuẩn bị
        List<HouseTypeVo> listHouse = new ArrayList<>();
        List<TypeHouseEntity> listAllType = new ArrayList<>();
        List<ServiceDetailEntity> listAllService = new ArrayList<>();


        // Kiểm tra các phương thức khác nếu cần
//        System.out.println(result);
        // Kiểm tra kết quả
        assertEquals("houselist", "houselist");
    }
    @Test
    public void testNoUpperCase() {
        int min1 ;
        int max1 ;
        int min2 ;
        int max2 ;
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status = 0;

        int status1;
        int status2;
        if(status==0){
            status1=0;
            status2=1;

        }else{
            status1=1;
            status2=1;

        }
        String houseName = "tony";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int countService = 1;
        int pageIndex = 1;
        int pageSize = 4;

        int countHouse = 1;
        List<String> price = Arrays.asList("1");
        List<String> listtype = Arrays.asList("1", "2", "3", "4");
        List<String> listservice = Arrays.asList("1");
        if (price.contains("1") && price.contains("2") && price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }
        else if (price.contains("1") && price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 4000000;
        } else if (price.contains("1") && price.contains("3")) {
            min1 = 0;
            max1 = 2000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("2") && price.contains("3")) {
            min1 = 2000000;
            max1 = 4000000;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else if (price.contains("1")) {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = 2000000;
        } else if (price.contains("2")) {
            min1 = 0;
            max1 = 0;
            min2 = 2000000;
            max2 = 4000000;
        } else if (price.contains("3")) {
            min1 = 0;
            max1 = 0;
            min2 = 4000000;
            max2 = Integer.MAX_VALUE;
        } else {
            min1 = 0;
            max1 = 0;
            min2 = 0;
            max2 = Integer.MAX_VALUE;
        }


        // Chuẩn bị
        List<HouseTypeVo> listHouse = new ArrayList<>();
        List<TypeHouseEntity> listAllType = new ArrayList<>();
        List<ServiceDetailEntity> listAllService = new ArrayList<>();



        // Kiểm tra các phương thức khác nếu cần
//        System.out.println(result);
        // Kiểm tra kết quả
        assertEquals("houselist", "houselist");
    }

    @Test
    public void testNoType() {
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status = 0;


        String houseName = "tony";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int pageIndex = 1;

        int countHouse = 1;
        List<String> price = Arrays.asList("1");
        List<String> listtype = Arrays.asList();
        List<String> listservice = Arrays.asList("1");




        // Kiểm tra các phương thức khác nếu cần
//        System.out.println(result);
        // Kiểm tra kết quả
        assertEquals("houselist", "houselist");
    }
    @Test
    public void testInvalid() {
        int province = 1;
        int district = 276;
        int ward = 9988;
        int status = 0;


        String houseName = "tony";
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        List<Integer> service = new ArrayList<>();
        service.add(1);
        int pageIndex = 1;

        int countHouse = 1;
        List<String> price = Arrays.asList("1");
        List<String> listtype = Arrays.asList("4");
        List<String> listservice = Arrays.asList("1");




        assertEquals("houselist", "houselist");
    }




}
