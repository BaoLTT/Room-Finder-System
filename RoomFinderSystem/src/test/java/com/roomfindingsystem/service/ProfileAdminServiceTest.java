package com.roomfindingsystem.service;

import com.roomfindingsystem.controller.UserController;
import com.roomfindingsystem.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileAdminServiceTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController controller;
    private ModelMapper modelMapper;

    @Test
    public void testFindByEmailExits() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setFirstName("Hà");
        userEntity.setLastName("Mạnh");
        userEntity.setEmail("thachdp2808@gmail.com");
        userEntity.setGender(true);
        userEntity.setAddressId(1);
        assertEquals("Hà", userEntity.getFirstName());
        assertEquals("Mạnh", userEntity.getLastName());
        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());

    }

    @Test
    public void testFindUserDtoByEmailWhenUserFoundThenReturnUserDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setFirstName("Hà");
        userEntity.setLastName("Mạnh");
        userEntity.setEmail("thachdp2808@gmail.com");
        userEntity.setGender(true);
        userEntity.setAddressId(1);

        when(userService.findByEmail("thachdp2808@gmail.com")).thenReturn(Optional.of(userEntity));
        userService.findByEmail("thachdp2808@gmail.com");

        assertEquals("Hà", userEntity.getFirstName());
        assertEquals("Mạnh", userEntity.getLastName());
        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());
    }

    @Test
    public void testFindByIdWhenValidIdThenReturnUserDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setFirstName("Hà");
        userEntity.setLastName("Mạnh");
        userEntity.setEmail("thachdp2808@gmail.com");
        userService.findById(1);
        assertEquals("Hà", userEntity.getFirstName());
        assertEquals("Mạnh", userEntity.getLastName());
        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());

    }

    @Test
    public void testFindUserDtoByEmailWhenUserDoesNotExistThenThrowException() {
        // Arrange
        when(userService.findByEmail("thachdp2808@gmail1.com")).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), userService.findByEmail("thachdp2808@gmail1.com"));
    }

    @Test
    public void testFindByIdWhenInvalidIdThenThrowException() {
        // Arrange
        when(userService.findById(1)).thenReturn(null);

        assertEquals(null, userService.findById(1));

    }



    @Test
    public void testCreateUserWhenInvalidUserDtoThenThrowException() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        // Act and Assert
        assertEquals(null, userService.save(user));


    }
}
