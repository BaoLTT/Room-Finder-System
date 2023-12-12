package com.roomfindersystem.service;

import com.roomfindersystem.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminManageUserService {
    List<UserDto> getAll();
    void deleteById(Integer id);
    void insertUser(UserDto userDto, MultipartFile file) throws IOException;

}
