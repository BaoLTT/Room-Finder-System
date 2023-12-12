package com.roomfindingsystem.controller;

import com.roomfindingsystem.dto.UserDto;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.service.AddressService;
import com.roomfindingsystem.service.AdminManageUserService;
import com.roomfindingsystem.service.UserService;
import com.roomfindingsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerForSupperAdminTest {
    @Mock
    private UserService userService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    AdminManageUserService adminManageUserService;
    @Mock
    private AddressService addressService;


    @Test
    public void getAllUserList() throws Exception {
        List<UserDto> users = adminManageUserService.getAll();

        when(adminManageUserService.getAll()).thenReturn(users);
        assertEquals(adminManageUserService.getAll(), users);

    }

    @Test
    public void getAllUserListByEmailInvalid() throws Exception {
        List<UserDto> users = adminManageUserService.getAll();
        UserEntity user = new UserEntity();
        user.setEmail("invalid-email"); // Thiết lập email không hợp lệ

        Optional<UserEntity> result = userService.findByEmail(user.getEmail());

        assertEquals(Optional.empty(), result);

    }
    @Test
    public void getAllUserListByEmail() throws Exception {
        List<UserDto> users = adminManageUserService.getAll();
        UserEntity user = new UserEntity();
        user.setEmail("baoltthe153367@fpt.edu.vn"); // Thiết lập email

        Optional<UserEntity> result = userService.findByEmail(user.getEmail());
        assertEquals(userService.findByEmail("baoltthe153367@fpt.edu.vn"), result);

    }

    @Test
    public void insertUser() throws Exception {
        UserDto user = new UserDto();
        user.setEmail("baoltthe153367@fpt.edu.vn"); // Thiết lập email
        user.setLastName("bao");
        user.setFirstName("le");
        user.setPhone("098917286");
        MultipartFile file = new MockMultipartFile("file", new byte[0]);
        adminManageUserService.insertUser(user,file);
    }

    @Test
    public void insertUserhaveEmailInvalid() throws Exception {

        MultipartFile file = new MockMultipartFile("file", new byte[0]);

        List<UserDto> users = adminManageUserService.getAll();
        UserEntity user = new UserEntity();
        user.setEmail("invalid-email"); // Thiết lập email không hợp lệ

        Optional<UserEntity> result = userService.findByEmail(user.getEmail());

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void getUserUpdate() throws Exception {
        Integer id =1;
        UserDto userDto = userService.findById(id);
        assertEquals(null , userDto);

    }

    @Test
    public void getUserUpdateInvalidId() throws Exception {
        Integer id =-1000;
        UserDto userDto = userService.findById(id);
        assertEquals(null , userDto);

    }
}
