package com.roomfindingsystem.service;

import com.roomfindingsystem.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminEditUserService {
    List<UserDto> getAll();
    void deleteById(Integer id);
    void insertUser(UserDto userDto, MultipartFile file) throws IOException;

}
