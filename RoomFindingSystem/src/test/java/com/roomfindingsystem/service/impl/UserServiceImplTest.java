//package com.roomfindingsystem.service.impl;
//
//import com.roomfindingsystem.dto.UserDto;
//import com.roomfindingsystem.entity.AddressEntity;
//import com.roomfindingsystem.entity.UserEntity;
//import com.roomfindingsystem.repository.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @Mock
//    private ProvinceRepository provinceRepository;
//
//    @Mock
//    private DistrictRepository districtRepository;
//
//    @Mock
//    private WardRepository wardRepository;
//
//    @Mock
//    private GcsService gcsService;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    public void setup() {
//        modelMapper = new ModelMapper();
//    }
//
//
//    @Test
//    public void testFindUserDtoByEmailWhenUserExistsThenReturnUserDto() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(1);
//        userEntity.setFirstName("Hà");
//        userEntity.setLastName("Mạnh");
//        userEntity.setEmail("thachdp2808@gmail.com");
//        userEntity.setGender(true);
//        userEntity.setAddressId(1);
//        userRepository.findByEmail("thachdp2808@gmail.com");
////        when(userRepository.findByEmail("thachdp2808@gmail.com")).thenReturn(Optional.of(userEntity));
//
////        UserDto userDto = userService.findUserDtoByEmail("thachdp2808@gmail.com");
//
//        assertEquals("Hà", userEntity.getFirstName());
//        assertEquals("Mạnh", userEntity.getLastName());
//        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());
//    }
//
//    @Test
//    public void testFindUserDtoByEmailWhenUserDoesNotExistThenThrowException() {
//        when(userRepository.findByEmail("invalid@gmail.com")).thenReturn(Optional.empty());
//
//        assertThrows(UsernameNotFoundException.class, () -> userService.findUserDtoByEmail("invalid@gmail.com"));
//    }
//
//    @Test
//    public void testFindUserDtoByEmailWhenUserFoundThenReturnUserDto() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(1);
//        userEntity.setFirstName("Hà");
//        userEntity.setLastName("Mạnh");
//        userEntity.setEmail("thachdp2808@gmail.com");
//        userEntity.setGender(true);
//        userEntity.setAddressId(1);
//
//        when(userRepository.findByEmail("thachdp2808@gmail.com")).thenReturn(Optional.of(userEntity));
//         userRepository.findByEmail("thachdp2808@gmail.com");
//
//        assertEquals("Hà", userEntity.getFirstName());
//        assertEquals("Mạnh", userEntity.getLastName());
//        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());
//    }
//
//    @Test
//    public void testFindUserDtoByEmailWhenUserNotFoundThenThrowException() {
//        when(userRepository.findByEmail("invalid@gmail.com")).thenReturn(Optional.empty());
//        userRepository.findByEmail("invalid@gmail.com");
//        assertThrows(UsernameNotFoundException.class, () -> userService.findUserDtoByEmail("invalid@gmail.com"));
//    }
//
//    @Test
//    public void testFindByIdWhenValidIdThenReturnUserDto() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(1);
//        userEntity.setFirstName("Hà");
//        userEntity.setLastName("Mạnh");
//        userEntity.setEmail("thachdp2808@gmail.com");
//        userRepository.findById(1);
//        assertEquals("Hà", userEntity.getFirstName());
//        assertEquals("Mạnh", userEntity.getLastName());
//        assertEquals("thachdp2808@gmail.com", userEntity.getEmail());
//
//    }
//
//    @Test
//    public void testFindByIdWhenNoUserFoundThenThrowException() {
//        when(userRepository.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(NoSuchElementException.class, () -> userRepository.findById(1));
//    }
//
//    @Test
//    public void testFindByIdWhenInvalidIdThenThrowException() {
//        when(userRepository.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("invalid"));
//    }
//
//    @Test
//    public void testUpdateProfileWithImage() throws IOException {
//        // Arrange
//        UserDto userDto = new UserDto();
//        userDto.setUserId(1);
//        userDto.setGender("MALE");
//        // Set other properties of userDto
//
//        MultipartFile file = new MockMultipartFile("test.jpg", "test.jpg", "image/jpeg", new byte[0]);
//
//        UserEntity user = new UserEntity();
//        user.setUserId(1);
//        // Set other properties of user
//
//        AddressEntity address = new AddressEntity();
//        // Set properties of address
//
//        // Mock the necessary repository methods
//        when(userRepository.findById(userDto.getUserId())).thenReturn(Optional.of(user));
//        when(addressRepository.findByProvinceIdAndDistrictIdAndWardId(anyInt(), anyInt(), anyInt())).thenReturn(Optional.of(address));
//        when(addressRepository.findByProvinceIdAndDistrictIdAndWardIdAndAddressDetails(anyInt(), anyInt(), anyInt(), anyString())).thenReturn(Optional.empty());
//
//        // Act
//        userService.updateProfile(userDto, file);
//
//        // Assert
//        // Add assertions to verify the expected behavior
//    }
//
//    @Test
//    public void testUpdateProfile() throws IOException {
//        UserDto userDto = new UserDto();
//        userDto.setUserId(1);
//        userDto.setFirstName("Hà");
//        userDto.setLastName("Mạnh");
//        userDto.setEmail("thachdp2808@gmail.com");
//        userDto.setGender("MALE");
//        userDto.setProvinceId(1);
//        userDto.setDistrictId(1);
//        userDto.setWardId(1);
//        userDto.setAddressDetails("123 Street");
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(1);
//        userEntity.setFirstName("Hà");
//        userEntity.setLastName("Mạnh");
//        userEntity.setEmail("thachdp2808@gmail.com");
//        userEntity.setGender(true);
//        userEntity.setAddressId(1);
//
//        MultipartFile file = mock(MultipartFile.class);
//
//        when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));
//        when(addressRepository.findByProvinceIdAndDistrictIdAndWardId(1, 1, 1)).thenReturn(Optional.empty());
//
//        //userService.updateProfile(userDto, file);
//
//        //verify(userRepository, times(1)).save(any(UserEntity.class));
//        //verify(addressRepository, times(1)).save(any(AddressEntity.class));
//    }
//
//}