//package com.roomfindingsystem.controller;
//
//import com.roomfindingsystem.dto.UserDto;
//import com.roomfindingsystem.service.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    private UserDto userDto;
//
//
//    @BeforeEach
//    public void setup() {
//        userDto = new UserDto();
//        userDto.setUserId(1);
//        userDto.setFirstName("John");
//        userDto.setLastName("Doe");
//        userDto.setEmail("john.doe@example.com");
//
//    }
//
//
//    @Test
//    public void testGetId() {
//        userDto.getUserId();
//        Assertions.assertEquals(1, userDto.getUserId());
//    }
//
//    @Test
//    public void testGetProfilePageWhenUserIsAuthenticatedThenReturnProfileView() throws Exception {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        when(authentication.getName()).thenReturn(userDto.getEmail());
//        SecurityContextHolder.setContext(securityContext);
//        when(userService.findUserDtoByEmail(userDto.getEmail())).thenReturn(userDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("profile"))
//                .andExpect(MockMvcResultMatchers.model().attribute("user", userDto));
//
//        Mockito.verify(userService, Mockito.times(1)).findUserDtoByEmail(userDto.getEmail());
//    }
//
//    @Test
//    public void testGetProfilePageWhenUserIsNotAuthenticatedThenRedirectToLoginPage() throws Exception {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(null);
//        SecurityContextHolder.setContext(securityContext);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
//    }
//
//    @Test
//    public void testUpdateUserWhenValidDataThenSuccess() throws Exception {
//        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
//
//        mockMvc.perform(MockMvcRequestBuilders.multipart("/user/update")
//                        .file(file)
//                        .flashAttr("user", userDto))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile"));
//
//        Mockito.verify(userService, Mockito.times(1)).updateProfile(userDto, file);
//    }
//
//    @Test
//    public void testUpdateUserWhenInvalidDataThenNoUpdate() throws Exception {
//        userDto.setEmail(""); // invalid email
//        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
//
//        mockMvc.perform(MockMvcRequestBuilders.multipart("/user/update")
//                        .file(file)
//                        .flashAttr("user", userDto))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile"));
//
//        Mockito.verify(userService, Mockito.never()).updateProfile(userDto, file);
//    }
//
//    @Test
//    public void testUpdateUserWhenNoFileThenSuccess() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.multipart("/user/update")
//                        .flashAttr("user", userDto))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile"));
//
//        Mockito.verify(userService, Mockito.times(1)).updateProfile(userDto, null);
//    }
//
//    @Test
//    public void testGetProfilePageWhenUserAuthenticatedThenSuccess() throws Exception {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        when(authentication.getName()).thenReturn(userDto.getEmail());
//        SecurityContextHolder.setContext(securityContext);
//        when(userService.findUserDtoByEmail(userDto.getEmail())).thenReturn(userDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attribute("user", userDto));
//
//        Mockito.verify(userService, Mockito.times(1)).findUserDtoByEmail(userDto.getEmail());
//    }
//
//    @Test
//    public void testGetProfilePageWhenUserNotAuthenticatedThenRedirectToLoginPage() throws Exception {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(null);
//        SecurityContextHolder.setContext(securityContext);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
//    }
//}