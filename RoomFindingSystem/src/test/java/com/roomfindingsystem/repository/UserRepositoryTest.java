package com.roomfindingsystem.repository;


import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

   @Autowired
   private  UserRepository userRepository;


   @Test
   void testFindByEmailWhenEmailExists() {
      // Arrange
      String emailToFind = "baoltthe153367@fpt.edu.vn";
      UserEntity userEntity = new UserEntity();
//      userEntity.setEmail(emailToFind);
      userEntity.setUserId(4);

      // Act
      Optional<UserEntity> result = userRepository.findByEmail(emailToFind);


      // Assert
      assertTrue(result.isPresent());
//      assertEquals(emailToFind, result.get().getEmail());
      assertEquals(4, result.get().getUserId());
      // Add more assertions based on your business logic and expectations
   }


   @Test
   void testFindByEmailWhenEmailDoesNotExist() {
      // Arrange
      String nonExistentEmail = "nonexistent@example.com";

      // Act
      Optional<UserEntity> result = userRepository.findByEmail(nonExistentEmail);

      // Assert
      assertFalse(result.isPresent());
   }

   @Test
   void testFindByEmail_EmailIsNull() {
      Optional<UserEntity> result = userRepository.findByEmail(null);

      // Kiểm tra kết quả
      assertEquals(Optional.empty(), result);
   }

   @Test
   void testFindByEmail_EmailIsEmpty() {
      // Test Case 4: Email có giá trị trống

      // Gọi phương thức cần kiểm thử từ service
      Optional<UserEntity> result = userRepository.findByEmail("");

      // Kiểm tra kết quả
      assertFalse(result.isPresent());


   }

   @Test
   void testFindByEmail_EmailExceedsMaxLength() {
      // Test Case 5: Email có độ dài vượt quá giới hạn
      String longEmail = "a".repeat(256); // Giả sử giới hạn là 255 ký tự


      // Gọi phương thức cần kiểm thử từ service
      Optional<UserEntity> result = userRepository.findByEmail(longEmail);

      // Kiểm tra kết quả
      assertFalse(result.isPresent());


   }

   @Test
   void testFindByEmail_EmailCaseInsensitive() {
      // Test Case 7: Email tồn tại nhưng với ký tự hoa/thường khác nhau
      String originalEmail = "baoltthe153367@fpt.edu.vn";
      UserEntity userEntity = new UserEntity();
      userEntity.setEmail(originalEmail);


      String uppercaseEmail = "BAOLTTHE153367@FPT.EDU.VN";

      Optional<UserEntity> result = userRepository.findByEmail(uppercaseEmail);

      assertTrue(result.isPresent());
      assertEquals(originalEmail, result.get().getEmail());

   }

   //save()
   @Test
   public void testSaveUser_Success() {
      // Arrange
      UserEntity user = new UserEntity();
      user.setFirstName("Le");
      user.setLastName("Kaka");

      // Act
      UserEntity userToSave = userRepository.save(user);

      try {
         // Assert
         assertNotNull(userToSave.getUserId());

         // Kiểm tra các thông tin khác của user nếu cần
         assertEquals(user.getFirstName(), userToSave.getFirstName());
         assertEquals(user.getLastName(), userToSave.getLastName());

         // Act - Lấy thông tin user sau khi đã lưu vào cơ sở dữ liệu
         UserEntity savedUser = userRepository.findById(userToSave.getUserId()).orElse(null);
         assertNotNull(savedUser);
         assertEquals(userToSave.getLastName(), savedUser.getLastName());

      } finally {
         // Clean up - Xóa user sau khi kiểm thử
         if (userToSave != null) {
            userRepository.deleteById(userToSave.getUserId());
         }
      }
   }

   //getUserForChangePass
   @Test
   void testGetUserForChangePass_ValidEmail() {
      // Arrange
      String email = "test@example.com";
      UserEntity userEntity = new UserEntity();
      userEntity.setEmail(email);


      String result = userRepository.getUserEntitiesByUserId(email);

      // Assert
      assertEquals(userEntity.getPassword(), result);

   }

   @Test
   void testGetUserForChangePass_InvalidEmail() {
      // Arrange
      String email = "nonexistent@example.com";


      // Act
      String result = userRepository.getUserEntitiesByUserId(email);

      // Assert
      assertNull(result);

   }

   @Test
   void testGetUserForChangePass_NullEmail() {
      // Arrange
      String email = null;

      // Act
      String result = userRepository.getUserEntitiesByUserId(email);

      // Assert
      assertNull(result);
//        verify(userRepository, never()).getUserEntitiesByUserId(email);
   }

   @Test
   public void testRecoverPassword_ValidCredentials() {
      // Arrange
      String validPassword = "123456";
      String validEmail = "thaibaoa3k45@gmail.com";


      // Act
      int result = userRepository.updatePassword(validPassword, validEmail);

      // Assert
      assertEquals(1, result);

   }
}
